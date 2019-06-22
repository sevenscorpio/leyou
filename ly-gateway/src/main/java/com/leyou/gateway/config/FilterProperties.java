package com.leyou.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.gateway.config
 * @ClassName: FilterProperties
 * @Author:
 * @Description:
 * @Date: 2019-05-22 6:29
 * @Version: 1.0
 */

@Data
@ConfigurationProperties(prefix = "ly.filter")
public class FilterProperties {

    private List<String> allowPaths;
}
