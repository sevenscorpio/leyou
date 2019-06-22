package com.leyou.auth.config;

import com.leyou.auth.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
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

    private String secret;
    private String pubKeyPath;
    private String priKeyPath;
    private int expire;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private String cookieName;

    // 对象一旦实例化后，就应该去读取公钥和私钥
    @PostConstruct
    public void init(){

        try {

            // 如果公钥私钥不存在，先生成
            File pubPath = new File(pubKeyPath);
            File priPath = new File(priKeyPath);
            if (!pubPath.exists() || !priPath.exists()){

                // 生成公钥和私钥
                RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
            }

            // 读取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);

        }catch (Exception e){
            log.error("初始化公钥和私钥失败", e);
            throw new RuntimeException();
        }


    }
}
