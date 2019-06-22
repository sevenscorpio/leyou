package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.api
 * @ClassName: CategoryApi
 * @Author:
 * @Description:
 * @Date: 2019-04-29 4:53
 * @Version: 1.0
 */
public interface CategoryApi {

    @GetMapping("category/list/ids")
    List<Category> queryCategoryByIds(@RequestParam("ids") List<Long> ids);
}
