package com.leyou.cart.pojo;

import lombok.Data;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.cart.pojo
 * @ClassName: Cart
 * @Author:
 * @Description:
 * @Date: 2019-05-25 7:39
 * @Version: 1.0
 */

@Data
public class Cart {

    private Long skuId;// 商品id
    private String title;// 标题
    private String image;// 图片
    private Long price;// 加入购物车时的价格
    private Integer num;// 购买数量
    private String ownSpec;// 商品规格参数
}
