package com.leyou.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.registry
 * @ClassName: LyRegistry
 * @Author:
 * @Description:
 * @Date: 2019-04-18 2:54
 * @Version: 1.0
 */

@EnableEurekaServer
@SpringBootApplication
public class LyRegistry {

    public static void main(String[] args) {
        SpringApplication.run(LyRegistry.class, args);
    }
}
