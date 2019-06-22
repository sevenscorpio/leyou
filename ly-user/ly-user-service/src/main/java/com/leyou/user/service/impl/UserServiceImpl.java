package com.leyou.user.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import com.leyou.user.utils.CodecUtils;
import com.leyou.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.user.service.impl
 * @ClassName: UserServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-05-17 8:19
 * @Version: 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:phone:";

    @Override
    public Boolean checkData(String data, Integer type) {

        User record = new User();

        //判断数据类型
        switch (type){
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            default:
                throw new LyException(ExceptionEnum.INVALID_USER_DATA_TYPE);
        }

        int count = userMapper.selectCount(record);

        return count == 0;
    }

    @Override
    public void sendCode(String phone) {

        // 生成key
        String key = KEY_PREFIX + phone;

        // 生成验证码
        String code = NumberUtils.generateCode(6);

        Map<String,String> msg = new HashMap<>();

        msg.put("phone", phone);
        msg.put("code", code);

        amqpTemplate.convertAndSend("ly.sms.exchange", "sms.verify.code", msg);

        //保存验证码，时效五分钟
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);

    }

    @Override
    public void register(User user, String code) {
        // 从redis中取出验证码
        String cacheCode = redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());

        //效验验证码
        if (!StringUtils.equals(code, cacheCode)){
            throw new LyException(ExceptionEnum.INVALID_VERIFY_ERRO);
        }

        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        //对密码加密
        String password = CodecUtils.md5Hex(user.getPassword(), salt);
        user.setPassword(password);

        //写入数据库
        user.setCreated(new Date());
        int count = userMapper.insert(user);

    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {

        // 查询用户
        User record = new User();
        record.setUsername(username);

        User user = userMapper.selectOne(record);

        // 校验
        if (user == null){
            throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }

        // 校验密码
        boolean result = StringUtils.equals(user.getPassword(), CodecUtils.md5Hex(password, user.getSalt()));
        if (!result){
            throw new LyException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }

        return user;
    }
}
