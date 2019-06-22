package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.pojo
 * @ClassName: SpuDetail
 * @Author:
 * @Description:
 * @Date: 2019-04-23 17:20
 * @Version: 1.0
 */

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {

    @Id
    private Long spuId;// 对应的SPU的id
    private String description;// 商品描述
    private String specialSpec;// 商品特殊规格的名称及可选值模板
    private String genericSpec;// 商品的全局规格属性
    private String packingList;// 包装清单
    private String afterService;// 售后服务
}
