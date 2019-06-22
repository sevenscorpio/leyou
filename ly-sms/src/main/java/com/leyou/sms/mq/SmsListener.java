package com.leyou.sms.mq;

import com.leyou.common.utils.JsonUtils;
import com.leyou.sms.config.SmsProperyies;
import com.leyou.sms.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.sms.mq
 * @ClassName: SmsListener
 * @Author:
 * @Description:
 * @Date: 2019-05-17 1:22
 * @Version: 1.0
 */

@Slf4j
@Component
@EnableConfigurationProperties(SmsProperyies.class)
public class SmsListener {

    @Autowired
    private SmsListener smsListener;

    @Autowired
    private SmsProperyies smsProperyies;

    @Autowired
    private SmsUtils smsUtils;

    /**
     *
     * @param msg
     * @Return: void
     * @Author:
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sms.verify.code.queue",durable = "true"),
            exchange = @Exchange(name = "ly.sms.exchange",type = ExchangeTypes.TOPIC),
            key = {"sms.verify.code"}
    ))
    public void listenInsertOrUpdate(Map<String,String> msg){

        if(CollectionUtils.isEmpty(msg)){
            return;
        }

        String phone = msg.remove("phone");
        if (StringUtils.isBlank(phone)){
            return;
        }

        smsUtils.sendSms(phone, smsProperyies.getSignName(), smsProperyies.getVerifyCodeTemplate(), JsonUtils.toString(msg));

    }
}
