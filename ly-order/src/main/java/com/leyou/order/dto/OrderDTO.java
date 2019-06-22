package com.leyou.order.dto;

import com.leyou.common.dto.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.dto
 * @ClassName: OrderDto
 * @Author:
 * @Description:
 * @Date: 2019-05-26 19:34
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull
    private Long addressId; // 收货人地址id

    @NotNull
    private Integer paymentType; // 付款类型

    @NotNull
    private List<CartDTO> carts; // 订单详情
}
