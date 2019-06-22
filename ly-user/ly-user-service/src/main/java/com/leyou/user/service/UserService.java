package com.leyou.user.service;

import com.leyou.user.pojo.User;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.user.service
 * @ClassName: UserService
 * @Author:
 * @Description:
 * @Date: 2019-05-17 8:19
 * @Version: 1.0
 */
public interface UserService {
    public Boolean checkData(String data, Integer type);

    public void sendCode(String phone);

    public void register(User user, String code);

    public User queryUserByUsernameAndPassword(String username, String password);
}
