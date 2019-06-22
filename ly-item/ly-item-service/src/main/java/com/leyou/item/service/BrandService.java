package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service
 * @ClassName: BrandService
 * @Author:
 * @Description:
 * @Date: 2019-04-19 21:04
 * @Version: 1.0
 */
public interface BrandService {

    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    public void addBrand(Brand brand, List<Long> cids);

    public Brand queryById(Long id);

    public List<Brand> queryBrandByCid(Long cid);

    public List<Brand> queryBrandByIds(List<Long> ids);
}
