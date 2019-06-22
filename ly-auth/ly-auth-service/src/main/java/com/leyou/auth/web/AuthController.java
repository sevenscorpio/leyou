package com.leyou.auth.web;

import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.pojo.UserInfo;
import com.leyou.auth.service.AuthService;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.auth.web
 * @ClassName: AuthController
 * @Author:
 * @Description:
 * @Date: 2019-05-21 4:39
 * @Version: 1.0
 */

@Slf4j
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthService authService;

    @Value("${ly.jwt.cookieName}")
    private String cookieName;

    /**
     * 登录授权
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<Void> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response,
            HttpServletRequest request
    ){

        String token = authService.login(username, password);

        // 将token写入cookie
        CookieUtils.newBuilder(response).httpOnly().request(request)
                .build(cookieName, token);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 效验用户登录状态
     * @param token
     * @Return: org.springframework.http.ResponseEntity<com.leyou.auth.pojo.UserInfo>
     * @Author:
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(
            @CookieValue("LY_TOKEN") String token,
            HttpServletResponse response,
            HttpServletRequest request
    ){

        if (StringUtils.isBlank(token)) {
            throw new LyException(ExceptionEnum.UNAUTHORIZED);
        }

        try {
            // 解析token
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());

            // 生成新的token
            String newToken = JwtUtils.generateToken(
                    userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());

            // 将token写入cookie
            CookieUtils.newBuilder(response).httpOnly().request(request)
                    .build(cookieName, newToken);

            return ResponseEntity.ok(userInfo);

        } catch (Exception e) {

            // token已过期或无效
            throw new LyException(ExceptionEnum.UNAUTHORIZED);
        }

    }

}
