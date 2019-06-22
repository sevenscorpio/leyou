package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 商品规格分组表
 * @ProjectName: leyou
 * @Package: com.leyou.item.pojo
 * @ClassName: SpecGroup
 * @Author:
 * @Description:
 * @Date: 2019-04-23 10:34
 * @Version: 1.0
 */

@Data
@Table(name = "tb_spec_group")
public class SpecGroup {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long cid;

    private String name;

    @Transient
    private List<SpecParam> params;
}
