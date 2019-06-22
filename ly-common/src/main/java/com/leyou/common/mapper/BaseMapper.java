package com.leyou.common.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;


/**
 * @ProjectName: leyou
 * @Package: com.leyou.common.mapper
 * @ClassName: BaseMapper
 * @Author:
 * @Description:
 * @Date: 2019-04-24 17:06
 * @Version: 1.0
 */

@RegisterMapper
public interface BaseMapper<T> extends Mapper<T>, IdListMapper<T,Long>, InsertListMapper<T> {
}
