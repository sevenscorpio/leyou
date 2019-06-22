package com.leyou.order.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.order.client
 * @ClassName: GoodsClient
 * @Author:
 * @Description:
 * @Date: 2019-05-26 21:44
 * @Version: 1.0
 */

@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
