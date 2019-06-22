package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品规格参数表
 * @ProjectName: leyou
 * @Package: com.leyou.item.pojo
 * @ClassName: SpecParam
 * @Author:
 * @Description:
 * @Date: 2019-04-23 11:44
 * @Version: 1.0
 */

@Data
@Table(name = "tb_spec_param")
public class SpecParam {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @Column(name = "`numeric`")  // numeric是关键字，反引号是转义为字符串
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
}
