package com.leyou.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.dto
 * @ClassName: CartDTO
 * @Author:
 * @Description:
 * @Date: 2019-05-26 19:38
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long skuId; // 商品skuId
    private Integer num; // 购买数量
}
