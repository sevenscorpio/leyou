package com.leyou.cart.service;

import com.leyou.cart.pojo.Cart;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.cart.service
 * @ClassName: CartService
 * @Author:
 * @Description:
 * @Date: 2019-05-25 7:41
 * @Version: 1.0
 */
public interface CartService {
    public void addCart(Cart cart);

    public List<Cart> queryCartList();

    public void updateCartNum(Long skuId, Integer num);

    public void deleteCart(Long skuId);
}
