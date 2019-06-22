package com.leyou.user.web;

import com.leyou.user.pojo.User;
import com.leyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.user.web
 * @ClassName: UserController
 * @Author:
 * @Description:
 * @Date: 2019-05-17 8:20
 * @Version: 1.0
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验数据是否存在
     * @param data
     * @param type
     * @Return: org.springframework.http.ResponseEntity<java.lang.Boolean>
     * @Author:
     */
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkData(
            @PathVariable("data")   String data,
            @PathVariable("type")   Integer type
    ){
        return ResponseEntity.ok(userService.checkData(data, type));
    }

    /**
     * 发送短信
     * @param phone
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PostMapping("code")
    public ResponseEntity<Void> sendCode(@RequestParam("phone") String phone){

        userService.sendCode(phone);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 用户注册
     * @param user
     * @param code
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code){
        userService.register(user, code);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/query")
    public ResponseEntity<User> queryUserByUsernameAndPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        return ResponseEntity.ok(userService.queryUserByUsernameAndPassword(username,password));
    }
}
