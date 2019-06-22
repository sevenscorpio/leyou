package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.pojo
 * @ClassName: Stock
 * @Author:
 * @Description:
 * @Date: 2019-04-24 15:43
 * @Version: 1.0
 */

@Data
@Table(name = "tb_stock")
public class Stock {

    @Id
    private Long skuId;
    private Integer seckillStock;   //秒杀可用库存
    private Integer seckillTotal;   //已秒杀库存
    private Integer stock;  //库存

}
