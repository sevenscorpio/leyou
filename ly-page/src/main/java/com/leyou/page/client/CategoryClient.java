package com.leyou.page.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.client
 * @ClassName: CategoryClient
 * @Author:
 * @Description:
 * @Date: 2019-04-28 19:23
 * @Version: 1.0
 */

@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
