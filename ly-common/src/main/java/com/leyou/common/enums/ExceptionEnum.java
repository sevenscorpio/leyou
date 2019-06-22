package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.common.enums
 * @ClassName: ExceptionEnums
 * @Author:
 * @Description:
 * @Date: 2019-04-18 7:13
 * @Version: 1.0
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    INVALID_FILE_TYPE(400,"无效的文件类型"),
    INVALID_USER_DATA_TYPE(400,"无效的用户数据类型"),
    GOODS_ID_CANNOT_BE_NULL(400,"商品ID不能为空"),
    INVALID_VERIFY_ERRO(400,"验证码有误"),
    INVALID_USERNAME_PASSWORD(400,"用户名或密码错误"),
    ORDER_STATUS_ERROR(400,"订单状态不正确"),
    INVALID_SIGN_ERROE(400,"无效的签名异常"),
    INVALID_ORDER_PARAM(400,"订单参数异常"),
    UNAUTHORIZED(403,"未授权"),
    CATEGORY_NOT_FOUND(404,"分类信息不存在"),
    BRAND_NOT_FOUND(404,"品牌不存在"),
    SPEC_GROUP_NOT_FOUND(404,"商品规格组不存在"),
    PARAM_GROUP_NOT_FOUND(404,"商品规格参数不存在"),
    GOODS_NOT_FOUND(404,"商品不存在"),
    GOODS_DETAIL_NOT_FOUND(404,"商品详情不存在"),
    GOODS_SKU_NOT_FOUND(404,"商品SKU不存在"),
    GOODS_SPU_NOT_FOUND(404,"商品SPU不存在"),
    GOODS_STOCK_NOT_FOUND(404,"商品库存不存在"),
    USER_NOT_FOUND(404,"用户不存在"),
    CART_NOT_FOUND(404,"购物车为空"),
    ORDER_NOT_FOUND(404,"订单不存在"),
    ORDER_DETAIL_NOT_FOUND(404,"订单详情不存在"),
    ORDER_STATUS_NOT_FOUND(404,"订单状态不存在"),
    BRAND_SAVE_ERROR(500,"添加品牌失败"),
    FILE_UPLOAD_ERROR(500,"文件上传失败"),
    GOODS_SAVE_ERROR(500,"添加商品失败"),
    GOODS_UPDATE_ERROR(500,"更新商品失败"),
    CREATE_TOKEN_ERROR(500,"用户凭证生成失败"),
    CREATE_ORDER_ERROR(500,"创建订单失败"),
    CREATE_ORDER_DETAIL_ERROR(500,"创建订单详情失败"),
    STOCK_NOT_ENOUGH(500,"库存不足"),
    WX_PAY_ORDER_FAIL(500,"微信下单失败"),
    UPDATE_ORDER_STATUS_ERROE(500,"更新订单状态失败"),

    ;
    private int code;
    private String message;
}
