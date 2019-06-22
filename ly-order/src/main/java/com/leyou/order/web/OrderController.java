package com.leyou.order.web;

import com.leyou.order.dto.OrderDTO;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderStatus;
import com.leyou.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.web
 * @ClassName: OrderController
 * @Author:
 * @Description:
 * @Date: 2019-05-26 19:43
 * @Version: 1.0
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param orderDTO
     * @Return: org.springframework.http.ResponseEntity<java.lang.Long>
     * @Author:
     */
    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    /**
     * 根据订单id查询订单
     * @param orderId
     * @Return: org.springframework.http.ResponseEntity<com.leyou.order.pojo.Order>
     * @Author:
     */
    @GetMapping("{id}")
    public ResponseEntity<Order> queryOrderByOrderId(@PathVariable("id") Long orderId){
        return ResponseEntity.ok(orderService.queryOrderByOrderId(orderId));
    }

    /**
     * 创建支付链接
     * @param orderId
     * @Return: org.springframework.http.ResponseEntity<com.leyou.order.pojo.OrderStatus>
     * @Author:
     */
    @GetMapping("/url/{id}")
    public ResponseEntity<String> createPayUrl(@PathVariable("id") Long orderId){
        return ResponseEntity.ok(orderService.createPayUrl(orderId));
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Integer> queryOrderStatus(@PathVariable("id") Long orderId){
        return ResponseEntity.ok(orderService.queryOrderStatus(orderId).getValue());
    }
}
