package com.leyou.gateway.filters;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.CookieUtils;
import com.leyou.gateway.config.FilterProperties;
import com.leyou.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.gateway.filters
 * @ClassName: AuthFilter
 * @Author:
 * @Description:
 * @Date: 2019-05-22 5:24
 * @Version: 1.0
 */

@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class AuthFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    /**
     * 过滤器类型
     * @param
     * @Return: java.lang.String
     * @Author:
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE; // 前置过滤
    }

    /**
     * 过滤器顺序
     * @param
     * @Return: int
     * @Author:
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 是否要过滤
     * @param
     * @Return: boolean
     * @Author:
     */
    @Override
    public boolean shouldFilter() {

        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();

        // 获取request
        HttpServletRequest request = ctx.getRequest();

        // 获取请求的url路径
        String path = request.getRequestURI();

        // 判断是否放行，放行，则返回false
        return !isAllowPath(path);
    }

    private boolean isAllowPath(String path) {

        // 遍历白名单
        for (String allowPath : filterProperties.getAllowPaths()) {

            // 判断是否允许
            if (path.startsWith(allowPath)){
                return true;
            }
        }

        return false;
    }

    @Override
    public Object run() throws ZuulException {

        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();

        // 获取request
        HttpServletRequest request = ctx.getRequest();

        // 获取token
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());

        try {
            // 解析token
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {

            // 解析token失败，未登录，拦截
            ctx.setSendZuulResponse(false);

            // 返回状态码
            ctx.setResponseStatusCode(403);
        }

        // 效验权限

        return null;
    }
}
