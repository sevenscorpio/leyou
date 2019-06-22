package com.leyou.search.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.client
 * @ClassName: SpecificationApi
 * @Author:
 * @Description:
 * @Date: 2019-04-29 5:02
 * @Version: 1.0
 */

@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi {
}
