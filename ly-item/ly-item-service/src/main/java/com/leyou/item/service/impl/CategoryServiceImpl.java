package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import com.leyou.common.exception.LyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service.impl
 * @ClassName: CategoryServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-04-19 5:15
 * @Version: 1.0
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryListByPid(Long pid) {

        Category t = new Category();
        t.setParentId(pid);

        //查询条件：mapper会把对象中的非空属性作为查询条件
        List<Category> categories = categoryMapper.select(t);

        //判断结果
        if (CollectionUtils.isEmpty(categories)){

            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }

        return categories;
    }

    @Override
    public List<Category> queryByIds(List<Long> ids){

        List<Category> categories = categoryMapper.selectByIdList(ids);

        //判断结果
        if (CollectionUtils.isEmpty(categories)){

            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }

        return categories;
    }
}
