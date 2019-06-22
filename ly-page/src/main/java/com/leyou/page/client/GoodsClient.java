package com.leyou.page.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.client
 * @ClassName: GoodsClient
 * @Author:
 * @Description:
 * @Date: 2019-04-29 4:30
 * @Version: 1.0
 */

@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
