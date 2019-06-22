package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.gateway
 * @ClassName: LyGateway
 * @Author:
 * @Description:
 * @Date: 2019-04-18 3:04
 * @Version: 1.0
 */

@EnableZuulProxy
@SpringCloudApplication
public class LyGateway {

    public static void main(String[] args) {
        SpringApplication.run(LyGateway.class, args);
    }
}
