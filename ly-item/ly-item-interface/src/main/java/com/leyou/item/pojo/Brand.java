package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 品牌
 * @ProjectName: leyou
 * @Package: com.leyou.item.pojo
 * @ClassName: Brand
 * @Author:
 * @Description:
 * @Date: 2019-04-19 21:00
 * @Version: 1.0
 */

@Data
@Table(name = "tb_brand")
public class Brand {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String image;
    private Character letter;

}
