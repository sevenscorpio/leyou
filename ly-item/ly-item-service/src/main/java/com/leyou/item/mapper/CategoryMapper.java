package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.mapper
 * @ClassName: CategoryMapper
 * @Author:
 * @Description:
 * @Date: 2019-04-19 5:13
 * @Version: 1.0
 */
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category,Long> {
}
