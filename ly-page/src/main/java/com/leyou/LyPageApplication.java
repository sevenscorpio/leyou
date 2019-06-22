package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: leyou
 * @Package: com.leyou
 * @ClassName: LyPageApplication
 * @Author:
 * @Description:
 * @Date: 2019-05-10 0:32
 * @Version: 1.0
 */

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class LyPageApplication {

    public static void main(String[] args) {

        SpringApplication.run(LyPageApplication.class, args);
    }
}
