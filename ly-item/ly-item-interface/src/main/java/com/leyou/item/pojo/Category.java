package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品分类
 * @ProjectName: leyou
 * @Package: com.leyou.item.pojo
 * @ClassName: Category
 * @Author:
 * @Description:
 * @Date: 2019-04-19 4:57
 * @Version: 1.0
 */

@Data
@Table(name="tb_category")
public class Category {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;
}