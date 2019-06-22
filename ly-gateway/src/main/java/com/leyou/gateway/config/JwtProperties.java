package com.leyou.gateway.config;

import com.leyou.auth.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.auth.config
 * @ClassName: JwtConfig
 * @Author:
 * @Description:
 * @Date: 2019-05-21 4:05
 * @Version: 1.0
 */

@Slf4j
@Data
@ConfigurationProperties(prefix = "ly.jwt")
public class JwtProperties {

    private String pubKeyPath;
    private PublicKey publicKey;

    private String cookieName;

    // 对象一旦实例化后，就应该去读取公钥和私钥
    @PostConstruct
    public void init() throws Exception {

        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    }
}
