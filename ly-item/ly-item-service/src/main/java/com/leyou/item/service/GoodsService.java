package com.leyou.item.service;

import com.leyou.common.dto.CartDTO;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service
 * @ClassName: GoodsService
 * @Author:
 * @Description:
 * @Date: 2019-04-23 17:31
 * @Version: 1.0
 */
public interface GoodsService {
    public PageResult<Spu> querySpuPage(Integer page, Integer rows, Boolean saleable, String key);

    public void addGoods(Spu spu);

    public SpuDetail queryDetailById(Long id);

    public List<Sku> querySkuBySpuId(Long spuId);

    public void updateGoods(Spu spu);

    public Spu querySpuById(Long id);

    public List<Sku> querySkuByIds(List<Long> ids);

    public void decreaseStock(List<CartDTO> cartDTOS);
}
