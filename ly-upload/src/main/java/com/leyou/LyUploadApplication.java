package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou
 * @ClassName: LyUploadApplication
 * @Author:
 * @Description:
 * @Date: 2019-04-20 7:05
 * @Version: 1.0
 */

@EnableDiscoveryClient
@SpringBootApplication
public class LyUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyUploadApplication.class, args);
    }
}
