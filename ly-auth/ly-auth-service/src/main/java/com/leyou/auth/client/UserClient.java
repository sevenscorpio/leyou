package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.auth.client
 * @ClassName: UserClient
 * @Author:
 * @Description:
 * @Date: 2019-05-21 5:52
 * @Version: 1.0
 */

@FeignClient("user-service")
public interface UserClient extends UserApi {
}
