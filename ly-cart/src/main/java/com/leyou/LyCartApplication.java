package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou
 * @ClassName: LyCartApplication
 * @Author:
 * @Description:
 * @Date: 2019-05-25 4:57
 * @Version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
public class LyCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyCartApplication.class, args);
    }
}
