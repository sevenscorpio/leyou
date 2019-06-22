package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ProjectName: leyou
 * @Package: com.leyou
 * @ClassName: LyUserService
 * @Author:
 * @Description:
 * @Date: 2019-05-17 3:27
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.user.mapper")
public class LyUserService {
    public static void main(String[] args) {
        SpringApplication.run(LyUserService.class,args);
    }
}
