package com.leyou.common.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页泛型
 * view object
 * @ProjectName: leyou
 * @Package: com.leyou.common.vo
 * @ClassName: PageResult
 * @Author:
 * @Description:
 * @Date: 2019-04-19 21:14
 * @Version: 1.0
 */

@Data
public class PageResult<T> {

    private Long total; //总条数
    private Integer totalPage;  //总页数
    private List<T> items;  //当前页数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
