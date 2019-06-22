package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.web
 * @ClassName: BrandController
 * @Author:
 * @Description:
 * @Date: 2019-04-19 21:06
 * @Version: 1.0
 */

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页查询
     * 首字母搜索
     * @param page  当前页
     * @param rows  每页信息数
     * @param sortBy   排序字段
     * @param desc  排序方式
     * @param key   搜索内容
     * @Return: org.springframework.http.ResponseEntity<PageResult<Brand>>
     * @Author:
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false") boolean desc,
            @RequestParam(value = "key",required = false) String key
    ){

        PageResult<Brand> brands = brandService.queryBrandByPage(page,rows,sortBy,desc,key);

        return ResponseEntity.ok(brands);
    }

    /**
     * 添加品牌
     * @param brand
     * @param cids
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand , @RequestParam("cids") List<Long> cids){

        brandService.addBrand(brand,cids);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据cid查询品牌
     * @param cid
     * @Return: org.springframework.http.ResponseEntity<java.util.List<Brand>>
     * @Author:
     */
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }

    /**
     * 根据id查询品牌
     * @param id
     * @Return: org.springframework.http.ResponseEntity<Brand>
     * @Author:
     */
    @GetMapping("{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id") Long id){
        return ResponseEntity.ok(brandService.queryById(id));
    }

    /**
     * 根据品牌id集合查询品牌集合
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<Brand>>
     * @Author:
     */
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(brandService.queryBrandByIds(ids));
    }
}
