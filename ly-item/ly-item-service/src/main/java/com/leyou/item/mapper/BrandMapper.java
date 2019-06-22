package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import com.leyou.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.mapper
 * @ClassName: BrandMapper
 * @Author:
 * @Description:
 * @Date: 2019-04-19 21:04
 * @Version: 1.0
 */

public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 添加分类id和品牌id中间表信息
     * @param cid
     * @param bid
     * @Return: int
     * @Author:
     */
    @Insert("INSERT INTO tb_category_brand (category_id,brand_id) VALUE (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid,@Param("bid") Long bid);

    /**
     * 根据分类id查询品牌id
     * @param cid
     * @Return: java.util.List<Brand>
     * @Author:
     */
    @Select("SELECT b.* FROM tb_brand b INNER JOIN tb_category_brand cb ON b.id = cb.brand_id WHERE cb.category_id = #{cid}")
    List<Brand> queryBrandByCategoryId(@Param("cid") Long cid);
}
