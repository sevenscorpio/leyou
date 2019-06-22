package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: leyou
 * @Package: com.leyou
 * @ClassName: LyAuthApplication
 * @Author:
 * @Description:
 * @Date: 2019-05-18 22:58
 * @Version: 1.0
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LyAuthApplication {

    public static void main(String[] args) {

        SpringApplication.run(LyAuthApplication.class, args);
    }
}
