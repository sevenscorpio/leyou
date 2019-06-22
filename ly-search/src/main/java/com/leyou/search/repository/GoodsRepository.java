package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.repository
 * @ClassName: GoodsRepository
 * @Author:
 * @Description:
 * @Date: 2019-04-29 5:17
 * @Version: 1.0
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
