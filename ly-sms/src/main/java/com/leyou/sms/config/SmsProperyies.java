package com.leyou.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.smsConfig
 * @ClassName: SmsProperyies
 * @Author:
 * @Description:
 * @Date: 2019-05-16 4:28
 * @Version: 1.0
 */

@Data
@ConfigurationProperties(prefix = "ly.sms")
public class SmsProperyies {

    private String accessKeyId;

    private String accessKeySecret;

    private String signName;

    private String verifyCodeTemplate;
}
