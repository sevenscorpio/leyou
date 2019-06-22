package com.leyou.order.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.pojo
 * @ClassName: OrderStatus
 * @Author:
 * @Description:
 * @Date: 2019-05-26 18:41
 * @Version: 1.0
 */

@Data
@Table(name = "tb_order_status")
public class OrderStatus {

    @Id
    private Long orderId; // 订单id

    private Integer status;

    private Date createTime; // 创建时间

    private Date paymentTime; // 付款时间

    private Date consignTime; // 发货时间

    private Date endTime; // 交易结束时间

    private Date closeTime; // 交易关闭时间

    private Date commentTime; // 评论时间
}
