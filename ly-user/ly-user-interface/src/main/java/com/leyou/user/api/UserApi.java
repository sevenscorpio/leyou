package com.leyou.user.api;

import com.leyou.user.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.user.api
 * @ClassName: UserApi
 * @Author:
 * @Description:
 * @Date: 2019-05-21 5:45
 * @Version: 1.0
 */
public interface UserApi {

    @GetMapping("/query")
    User queryUserByUsernameAndPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
}
