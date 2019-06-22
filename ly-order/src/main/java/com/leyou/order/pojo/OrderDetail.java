package com.leyou.order.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.pojo
 * @ClassName: OrderDetail
 * @Author:
 * @Description:
 * @Date: 2019-05-26 18:28
 * @Version: 1.0
 */

@Data
@Table(name = "tb_order_detail")
public class OrderDetail {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long orderId; // 订单id
    private Long skuId; // 商品id
    private Integer num; // 商品购买数量
    private Long price; // 商品单价
    private String ownSpec; // 商品规格数量
    private String image; // 图片
    private String title;


}
