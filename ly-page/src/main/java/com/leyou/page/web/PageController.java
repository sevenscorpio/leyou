package com.leyou.page.web;

import com.leyou.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.page.web
 * @ClassName: PageController
 * @Author:
 * @Description:
 * @Date: 2019-05-10 3:11
 * @Version: 1.0
 */

@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id") Long spuId, Model model){

        //查询数据模型
        Map<String, Object> attributes = pageService.loadModel(spuId);

        //准备数据模型
        model.addAllAttributes(attributes);

        //返回视图
        return "item";
    }

}
