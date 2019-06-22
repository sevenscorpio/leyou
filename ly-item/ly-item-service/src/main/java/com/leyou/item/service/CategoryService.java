package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service
 * @ClassName: CategoryService
 * @Author:
 * @Description:
 * @Date: 2019-04-19 5:15
 * @Version: 1.0
 */
public interface CategoryService {

    public List<Category> queryCategoryListByPid(Long pid);

    public List<Category> queryByIds(List<Long> ids);
}
