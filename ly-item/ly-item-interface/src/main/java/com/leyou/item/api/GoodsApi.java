package com.leyou.item.api;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.api
 * @ClassName: GoodsApi
 * @Author:
 * @Description:
 * @Date: 2019-04-29 4:47
 * @Version: 1.0
 */
public interface GoodsApi {

    @GetMapping("/spu/detail/{id}")
    SpuDetail queryDetailById(@PathVariable("id") Long id);

    @GetMapping("/sku/list")
    List<Sku> querySkuBySpuId(@RequestParam("id") Long spuId);

    @GetMapping("/spu/page")
    PageResult<Spu> querySpuPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable",required = false) Boolean saleable,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows
    );

    /**
     * 根据spu的id查询spu
     * @param id
     * @Return: com.leyou.item.pojo.Spu
     * @Author:
     */
    @GetMapping("/spu/{id}")
    Spu querySpuById(@PathVariable("id") Long id);

    /**
     * 根据id集合批量查询sku
     * @param ids
     * @Return: java.util.List<com.leyou.item.pojo.Sku>
     * @Author:
     */
    @GetMapping("/sku/list/ids")
    List<Sku> querySkuByIds(@RequestParam("ids") List<Long> ids);

    /**
     * 减库存
     * @param cartDTOS
     * @Return: void
     * @Author:
     */
    @PostMapping("stock/decrease")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOS);
}
