package com.leyou.cart.web;

import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.cart.web
 * @ClassName: CartController
 * @Author:
 * @Description:
 * @Date: 2019-05-25 7:40
 * @Version: 1.0
 */

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 加入购物车
     * @param cart
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){

        cartService.addCart(cart);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询购物车
     * @param
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.cart.pojo.Cart>>
     * @Author:
     */
    @GetMapping("list")
    public ResponseEntity<List<Cart>> queryCartList(){
        return ResponseEntity.ok(cartService.queryCartList());
    }

    /**
     * 修改购物车的商品数量
     * @param skuId
     * @param num
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PutMapping
    public ResponseEntity<Void> updateCartNum(
            @RequestParam("id") Long skuId,
            @RequestParam("num") Integer num
    ){
        cartService.updateCartNum(skuId, num);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("{skuId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("skuId") Long skuId){
        cartService.deleteCart(skuId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
