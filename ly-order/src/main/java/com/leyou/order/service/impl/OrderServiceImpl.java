package com.leyou.order.service.impl;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.common.dto.CartDTO;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.order.enums.OrderStatusEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.IdWorker;
import com.leyou.item.pojo.Sku;
import com.leyou.order.client.AddressClient;
import com.leyou.order.client.GoodsClient;
import com.leyou.order.dto.OrderDTO;
import com.leyou.order.enums.PayState;
import com.leyou.order.interceptor.UserInterceptor;
import com.leyou.order.mapper.OrderDetailMapper;
import com.leyou.order.mapper.OrderMapper;
import com.leyou.order.mapper.OrderStatusMapper;
import com.leyou.order.pojo.AddressDTO;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderDetail;
import com.leyou.order.pojo.OrderStatus;
import com.leyou.order.service.OrderService;
import com.leyou.order.utils.PayHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.service.impl
 * @ClassName: OrderServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-05-26 19:46
 * @Version: 1.0
 */

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private PayHelper payHelper;

    @Override
    @Transactional
    public Long createOrder(OrderDTO orderDTO) {

        // 1.新增订单
        Order order = new Order();

        // 1.1 生成订单编号，初始化订单
        long orderId = idWorker.nextId();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setPaymentType(orderDTO.getPaymentType());

        // 1.2 用户信息
        UserInfo user = UserInterceptor.getUser();
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        order.setBuyerRate(false);

        // 1.3 收货人信息
        // 获取收货人信息
        AddressDTO address = AddressClient.findById(orderDTO.getAddressId());
        order.setReceiver(address.getName());
        order.setReceiverAddress(address.getAddress());
        order.setReceiverCity(address.getCity());
        order.setReceiverDistrict(address.getDistrict());
        order.setReceiverMobile(address.getPhone());
        order.setReceiverState(address.getState());
        order.setReceiverZip(address.getZipCode());

        // 1.4 金额
        // 把cartDTO转化成一个map，key是skuId，值是num
        Map<Long, Integer> cartMap = orderDTO.getCarts().stream()
                .collect(Collectors.toMap(CartDTO::getSkuId, CartDTO::getNum));

        // 获取所有sku的id
        Set<Long> ids = cartMap.keySet();

        // 根据id查询sku
        List<Sku> skus = goodsClient.querySkuByIds(new ArrayList<>(ids));

        // 准备orderDetail
        ArrayList<OrderDetail> details = new ArrayList<>();

        long totalPay = 0L;
        for (Sku sku : skus) {

            // 计算订单总价
            totalPay += sku.getPrice() * cartMap.get(sku.getId());

            // 封装orderDetail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setImage(StringUtils.substringBefore(sku.getImages(), ","));
            orderDetail.setNum(cartMap.get(sku.getId()));
            orderDetail.setOrderId(orderId);
            orderDetail.setOwnSpec(sku.getOwnSpec());
            orderDetail.setPrice(sku.getPrice());
            orderDetail.setSkuId(sku.getId());
            orderDetail.setTitle(sku.getTitle());
            details.add(orderDetail);

        }

        order.setTotalPay(totalPay);
        // 实付金额 = 总金额 + 邮费 - 优惠金额
        order.setActualPay(totalPay + order.getPostFee() - 0);

        // 1.5 将order写入数据库
        int count = orderMapper.insertSelective(order);
        if (count != 1){
            log.error("[创建订单] 创建订单失败，orderId:{}", orderId);
            throw new LyException(ExceptionEnum.CREATE_ORDER_ERROR);
        }

        // 2.新增订单详情
        count = orderDetailMapper.insertList(details);
        if (count != details.size()){
            log.error("[创建订单] 创建订单详情失败，orderId:{}", orderId);
            throw new LyException(ExceptionEnum.CREATE_ORDER_DETAIL_ERROR);
        }

        // 3.新增订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setCreateTime(order.getCreateTime());
        orderStatus.setOrderId(order.getOrderId());
        orderStatus.setStatus(OrderStatusEnum.UN_PAY.getCode());
        count = orderStatusMapper.insertSelective(orderStatus);
        if (count != 1){
            log.error("[创建订单] 创建订单状态失败，orderId:{}", orderId);
            throw new LyException(ExceptionEnum.CREATE_ORDER_DETAIL_ERROR);
        }

        // 4.删除购物车中已购买商品

        // 5.减库存
        List<CartDTO> cartDTOS = orderDTO.getCarts();
        goodsClient.decreaseStock(cartDTOS);
        return orderId;
    }

    @Override
    public Order queryOrderByOrderId(Long orderId) {

        // 查询订单
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null){
            throw new LyException(ExceptionEnum.ORDER_NOT_FOUND);
        }

        // 查询订单详情
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getOrderId());
        List<OrderDetail> orderDetails = orderDetailMapper.select(orderDetail);
        if (CollectionUtils.isEmpty(orderDetails)){
            throw new LyException(ExceptionEnum.ORDER_DETAIL_NOT_FOUND);
        }

        order.setOrderDetails(orderDetails);

        // 查询订单状态
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(order.getOrderId());
        if (orderStatus == null){
            throw new LyException(ExceptionEnum.ORDER_STATUS_NOT_FOUND);
        }

        order.setOrderStatus(orderStatus);
        return order;
    }

    @Override
    public String createPayUrl(Long orderId) {

        // 查询订单
        Order order = queryOrderByOrderId(orderId);

        // 判断订单状态
        Integer status = order.getOrderStatus().getStatus();
        if (status != OrderStatusEnum.UN_PAY.getCode()){
            // 订单状态不正确
            throw new LyException(ExceptionEnum.ORDER_STATUS_ERROR);
        }

        // 支付金额
        Long actualPay = /*order.getActualPay()*/ 1L;

        // 商品描述
        OrderDetail orderDetail = order.getOrderDetails().get(0);
        String title = orderDetail.getTitle();

        return payHelper.createOrder(orderId, actualPay, title);
    }

    @Override
    public void handleNotify(Map<String, String> result) {

        // 1 数据校验
        payHelper.isSuccess(result);

        // 2 校验签名
        payHelper.isValidSign(result);

        // 3 校验金额
        String totalFeeStr = result.get("total_fee");
        String tradeNo = result.get("out_trade_no");
        if (StringUtils.isEmpty(totalFeeStr) || StringUtils.isEmpty(tradeNo)){
            throw new LyException(ExceptionEnum.INVALID_ORDER_PARAM);
        }

        // 3.1 获取结果中的金额
        Long totalFee = Long.valueOf(totalFeeStr);

        // 3.2 获取订单金额
        Long orderId = Long.valueOf(tradeNo);

        Order order = orderMapper.selectByPrimaryKey(orderId);

        if (totalFee != /*order.getActualPay()*/ 1) {

            // 金额不符
            throw new LyException(ExceptionEnum.INVALID_ORDER_PARAM);
        }

        // 4 修改订单状态
        OrderStatus status = new OrderStatus();
        status.setStatus(OrderStatusEnum.PAYED.value());
        status.setOrderId(orderId);
        status.setPaymentTime(new Date());
        int count = orderStatusMapper.updateByPrimaryKeySelective(status);
        if (count != 1){
            throw new LyException(ExceptionEnum.UPDATE_ORDER_STATUS_ERROE);
        }

        log.info("[支付回调] 订单支付成功！ 订单编号：{}", orderId);
    }

    @Override
    public PayState queryOrderStatus(Long orderId) {

        // 查询订单状态
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(orderId);
        Integer status = orderStatus.getStatus();

        // 判断是否支付
        if (status != OrderStatusEnum.UN_PAY.value()){

            // 如果状态显示已支付，就是用户已支付
            return PayState.SUCCESS;
        }

        // 如果是未支付，不一定是没有付款，也有可能是微信的回调函数没有执行，所以必须去微信查询支付状态
        return payHelper.queryPayState(orderId);
    }

}
