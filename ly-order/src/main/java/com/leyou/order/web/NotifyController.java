package com.leyou.order.web;

import com.leyou.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.web
 * @ClassName: NotifyController
 * @Author:
 * @Description:
 * @Date: 2019-05-30 17:31
 * @Version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("notify")
public class NotifyController {

    @Autowired
    private OrderService orderService;

    /**
     * 微信支付的成功回调
     * @param result
     * @Return: java.lang.String
     * @Author:
     */
    @PostMapping(value = "pay", produces = "application/xml")
    public Map<String,String> handleNotify(@RequestBody Map<String,String> result){

        // 处理回调
        orderService.handleNotify(result);
        log.info("[支付回调] 接收微信支付回调，结果：{}", result);

        // 返回成功
        Map<String,String> msg = new HashMap<>();
        msg.put("return_code", "SUCCESS");
        msg.put("return_msg", "OK");

        return msg;
    }
}
