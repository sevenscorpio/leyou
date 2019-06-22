package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: leyou
 * @Package: com.leyou
 * @ClassName: LySearchApplication
 * @Author:
 * @Description:
 * @Date: 2019-04-28 18:27
 * @Version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LySearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(LySearchApplication.class, args);
    }
}
