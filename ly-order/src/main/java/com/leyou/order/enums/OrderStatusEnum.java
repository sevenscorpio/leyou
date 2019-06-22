package com.leyou.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.common.enums
 * @ClassName: OrderStatusEnum
 * @Author:
 * @Description:
 * @Date: 2019-05-27 23:55
 * @Version: 1.0
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatusEnum {

    UN_PAY(1,"未付款"),
    PAYED(2,"已付款，未发货"),
    DELIVERED(3,"已发货，未确认"),
    SUCCESS(4,"已确认，未评价"),
    CLOSED(5,"已失败，交易关闭"),
    RATED(6,"已评价"),
    ;
    private int code;
    private String desc;

    public int value(){
        return this.code;
    }
}
