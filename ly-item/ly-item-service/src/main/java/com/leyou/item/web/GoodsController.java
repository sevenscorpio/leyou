package com.leyou.item.web;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.vo.PageResult;
import com.leyou.item.service.GoodsService;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.web
 * @ClassName: GoodsController
 * @Author:
 * @Description:
 * @Date: 2019-04-23 17:34
 * @Version: 1.0
 */

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询SPU
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @Return: org.springframework.http.ResponseEntity<PageResult<Spu>>
     * @Author:
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable",required = false) Boolean saleable,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows


    ){
        return ResponseEntity.ok(goodsService.querySpuPage(page,rows,saleable,key));
    }

    /**
     * 添加商品
     * @param spu
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PostMapping("goods")
    public ResponseEntity<Void> addGoods(@RequestBody Spu spu){
        goodsService.addGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改商品
     * @param spu
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu){
        goodsService.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 根据spu的id查询详情detail
     * @param id
     * @Return: org.springframework.http.ResponseEntity<SpuDetail>
     * @Author:
     */
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id") Long id){
        return ResponseEntity.ok(goodsService.queryDetailById(id));
    }

    /**
     * 根据spu的id查询下面的所有sku
     * @param spuId
     * @Return: org.springframework.http.ResponseEntity<java.util.List<Sku>>
     * @Author:
     */
    @GetMapping("/sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long spuId){
        return ResponseEntity.ok(goodsService.querySkuBySpuId(spuId));
    }

    /**
     * 根据sku的ids查询下面的所有sku
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<Sku>>
     * @Author:
     */
    @GetMapping("/sku/list/ids")
    public ResponseEntity<List<Sku>> querySkuByIds(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(goodsService.querySkuByIds(ids));
    }

    /**
     * 根据spuid查询spu
     * @param id
     * @Return: org.springframework.http.ResponseEntity<Spu>
     * @Author:
     */
    @GetMapping("/spu/{id}")
    public ResponseEntity<Spu> querySpuById(@PathVariable("id") Long id){
        return ResponseEntity.ok(goodsService.querySpuById(id));
    }

    /**
     * 减库存
     * @param cartDTOS
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @Author:
     */
    @PostMapping("stock/decrease")
    public ResponseEntity<Void> decreaseStock(@RequestBody List<CartDTO> cartDTOS){
        goodsService.decreaseStock(cartDTOS);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
