package com.leyou.auth;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.auth.utils.RsaUtils;
import com.sun.glass.ui.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.auth
 * @ClassName: JwtTest
 * @Author:
 * @Description:
 * @Date: 2019-05-19 7:51
 * @Version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JwtTest {

    private static final String pubKeyPath = "E:\\leyou\\rsa\\rsa.pub";

    private static final String priKeyPath = "E:\\leyou\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        System.out.println(privateKey);
        System.out.println(publicKey);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
//        byte[] privateKey = Files.readAllBytes(new File(priKeyPath).toPath());
        String token = JwtUtils.generateToken(new UserInfo(20L,2L, "林Saber"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInJvbGUiOjIsInVzZXJuYW1lIjoi5p6XU2FiZXIiLCJleHAiOjE1NTgyMjY0MDF9.PWWvmNBx0EORMZLe2rTGuhxlvxlM2tqMy3VmxR-qUl443zUz6VpGLTO3_bLMfXBL_0d8J7jWTITckxp7LdvkDfZWy33voSBglePbd4jf2CTHyjeawkdjpqB6Nbc34DR-ArdMqCbZ6NGj8EeYo9h-F5zt7utj496fcRUUVngjV8A";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
