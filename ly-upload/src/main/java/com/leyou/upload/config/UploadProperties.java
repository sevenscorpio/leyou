package com.leyou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.upload.config
 * @ClassName: UploadProperties
 * @Author:
 * @Description:
 * @Date: 2019-04-21 4:40
 * @Version: 1.0
 */

@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {

    private String baseUrl;
    private List<String> allowTypes;
}
