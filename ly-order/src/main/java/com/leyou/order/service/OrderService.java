package com.leyou.order.service;

import com.leyou.order.dto.OrderDTO;
import com.leyou.order.enums.PayState;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderStatus;

import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.service
 * @ClassName: OrderService
 * @Author:
 * @Description:
 * @Date: 2019-05-26 19:45
 * @Version: 1.0
 */
public interface OrderService {
    public Long createOrder(OrderDTO orderDTO);

    public Order queryOrderByOrderId(Long orderId);

    public String createPayUrl(Long orderId);

    public void handleNotify(Map<String, String> result);

    public PayState queryOrderStatus(Long orderId);
}
