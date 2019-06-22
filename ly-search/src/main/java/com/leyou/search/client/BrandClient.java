package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.client
 * @ClassName: BrandClient
 * @Author:
 * @Description:
 * @Date: 2019-04-29 5:01
 * @Version: 1.0
 */

@FeignClient("item-service")
public interface BrandClient extends BrandApi {
}
