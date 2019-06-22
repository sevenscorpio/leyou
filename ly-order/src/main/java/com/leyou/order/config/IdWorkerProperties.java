package com.leyou.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.config
 * @ClassName: IdWorkerProperties
 * @Author:
 * @Description:
 * @Date: 2019-05-26 20:49
 * @Version: 1.0
 */

@Data
@ConfigurationProperties(prefix = "ly.worker")
public class IdWorkerProperties {

    private Long workerId; // 当前机器id
    private Long dataCenterId; // 序列号
}
