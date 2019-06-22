package com.leyou.search.web;

import com.leyou.common.vo.PageResult;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.service.SearchService;
import com.leyou.search.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.web
 * @ClassName: SearchController
 * @Author:
 * @Description:
 * @Date: 2019-05-02 17:13
 * @Version: 1.0
 */

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 搜索功能
     * @param searchRequest
     * @Return: org.springframework.http.ResponseEntity<PageResult<Goods>>
     * @Author:
     */
    @PostMapping("page")
    public ResponseEntity<PageResult<Goods>> search(@RequestBody SearchRequest searchRequest){

        return ResponseEntity.ok(searchService.search(searchRequest));
    }
}
