package com.leyou.search.pojo;

import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.search.pojo
 * @ClassName: SearchRequest
 * @Author:
 * @Description:
 * @Date: 2019-05-02 17:07
 * @Version: 1.0
 */
public class SearchRequest {

    private String key;

    private Integer page;

    private Map<String,String> filter;

    private static final Integer DEFAULT_SIZE = 20; //每页大小，不从页面接收，大小固定
    private static final Integer DEFAULT_PAGE = 1;  //默认页

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {

        if (page == null){
            return DEFAULT_PAGE;
        }

        //获取页码时的校验，页码不能小于1
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return DEFAULT_SIZE;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }
}
