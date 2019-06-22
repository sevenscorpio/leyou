package com.leyou.cart.service.impl;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.cart.interceptor.UserInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.cart.service.impl
 * @ClassName: CartServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-05-25 7:41
 * @Version: 1.0
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "cart:uid:";

    @Override
    public void addCart(Cart cart) {

        // 获取登录用户
        UserInfo user = UserInterceptor.getUser();

        // key
        String key = KEY_PREFIX + user.getId();

        // hashkey
        String hashkey = cart.getSkuId().toString();

        // 记录num
        Integer num = cart.getNum();

        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(key);

        // 判断当前购物车商品是否存在
        if (operations.hasKey(hashkey)) {

            // 存在，修改数量
            String json = operations.get(hashkey).toString();
            cart = JsonUtils.toBean(json, Cart.class);
            cart.setNum(num + cart.getNum());

        }

        // 不存在，添加到购物车
        operations.put(hashkey, JsonUtils.toString(cart));



    }

    @Override
    public List<Cart> queryCartList() {

        // 获取登录用户
        UserInfo user = UserInterceptor.getUser();

        // key
        String key = KEY_PREFIX + user.getId();

        if (!redisTemplate.hasKey(key)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }

        // 获取登录用户的所有购物车信息
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(key);

        List<Cart> carts = operations.values().stream()
                .map(o -> JsonUtils.toBean(o.toString(), Cart.class)).collect(Collectors.toList());

        return carts;
    }

    @Override
    public void updateCartNum(Long skuId, Integer num) {

        // 获取登录用户
        UserInfo user = UserInterceptor.getUser();

        // key
        String key = KEY_PREFIX + user.getId();

        // hashkey
        String hashKey = skuId.toString();

        // 获取redis里的数据
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(key);

        // 判断是否存在
        if (!operations.hasKey(hashKey)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }

        // 查询
        Cart cart = JsonUtils.toBean(operations.get(hashKey).toString(), Cart.class);
        cart.setNum(num);

        // 写回redis
        operations.put(hashKey, JsonUtils.toString(cart));

    }

    @Override
    public void deleteCart(Long skuId) {

        // 获取登录用户
        UserInfo user = UserInterceptor.getUser();

        // key
        String key = KEY_PREFIX + user.getId();

        // 删除
        redisTemplate.opsForHash().delete(key,skuId.toString());

    }
}


