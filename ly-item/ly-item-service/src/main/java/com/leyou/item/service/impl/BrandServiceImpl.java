package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service.impl
 * @ClassName: BrandServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-04-19 21:06
 * @Version: 1.0
 */

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询
     * 首字母搜索
     * @param page  当前页
     * @param rows  每页信息数
     * @param sortBy    排序字段
     * @param desc  排序方式
     * @param key   搜索内容
     * @Return: PageResult<Brand>
     * @Author:
     */
    @Override
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {

        //分页
        PageHelper.startPage(page,rows);

        //过滤
        /*
        * WHERE ‘name’ LIKE '%x%' OR letter = 'x'
        * ORDER BY id DESC
        * */

        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)){
            //过滤条件
            example.createCriteria().orLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }

        //排序
        if (StringUtils.isNotBlank(sortBy)){

            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }

        //查询
        List<Brand> brands = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(brands)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        //解析分页结果
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);

        return new PageResult<>(brandPageInfo.getTotal(), brands);
    }

    /**
     * 添加品牌
     * @param brand    品牌对象
     * @param cids     商品分类id
     * @Return: void
     * @Author:
     * 添加失败抛出异常
     */
    @Override
    @Transactional
    public void addBrand(Brand brand, List<Long> cids) {

        //将品牌id置空
        brand.setId(null);
        //添加品牌
        int count = brandMapper.insert(brand);
        //失败抛出异常
        if (count != 1){
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }

        //添加中间表
        for(Long cid : cids ) {
            //添加
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            //失败抛出异常
            if (count != 1) {
                throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }
    }

    @Override
    public Brand queryById(Long id) {

        Brand brand = brandMapper.selectByPrimaryKey(id);

        if (brand == null){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        return brand;
    }

    @Override
    public List<Brand> queryBrandByCid(Long cid) {

        //查询
        List<Brand> brands = brandMapper.queryBrandByCategoryId(cid);

        //判断 如果为空 抛出异常
        if (CollectionUtils.isEmpty(brands)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        return brands;
    }

    @Override
    public List<Brand> queryBrandByIds(List<Long> ids) {

        List<Brand> brands = brandMapper.selectByIdList(ids);

        if (CollectionUtils.isEmpty(brands)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        return brands;
    }

}
