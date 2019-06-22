package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ProjectName: leyou
 * @Package: com.leyou
 * @ClassName: LyItemService
 * @Author:
 * @Description:
 * @Date: 2019-04-18 3:57
 * @Version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper")
public class LyItemService {

    public static void main(String[] args) {
        SpringApplication.run(LyItemService.class, args);
    }
}
