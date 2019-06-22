package com.leyou.cart.interceptor;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.cart.config.JwtProperties;
import com.leyou.common.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.cart.interceptor
 * @ClassName: UserInterceptor
 * @Author:
 * @Description:
 * @Date: 2019-05-25 5:22
 * @Version: 1.0
 */

@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    private JwtProperties jwtProperties;

    private static final ThreadLocal<UserInfo> tl = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 获取cookie中的token
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());

        try {
            // 解析token
            UserInfo user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());

            //  传递user
            tl.set(user);

            return true;

        } catch (Exception e) {
            log.error("[购物车服务] 解析用户身份失败", e);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // 最后用完数据 一定要清空
        tl.remove();
    }

    public UserInterceptor(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public static UserInfo getUser(){
        return tl.get();
    }
}
