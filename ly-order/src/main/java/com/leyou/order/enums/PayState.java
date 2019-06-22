package com.leyou.order.enums;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.enums
 * @ClassName: PayState
 * @Author:
 * @Description:
 * @Date: 2019-05-31 2:54
 * @Version: 1.0
 */
public enum PayState {

    NOT_PAY(0),
    SUCCESS(1),
    FAIL(2);

    PayState(int value) {
        this.value = value;
    }

    int value;

    public int getValue() {
        return value;
    }
}
