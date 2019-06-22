package com.leyou.search.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.service
 * @ClassName: SearchService
 * @Author:
 * @Description:
 * @Date: 2019-04-29 5:35
 * @Version: 1.0
 */
public interface SearchService {

    public Goods buildGoods(Spu spu);

    public PageResult<Goods> search(SearchRequest searchRequest);

    public void createOrUpdateIndex(Long spuId);

    public void deleteIndex(Long spuId);
}
