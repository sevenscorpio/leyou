package com.leyou.page.service.impl;

import com.leyou.page.service.PageService;
import com.leyou.item.pojo.*;
import com.leyou.page.client.BrandClient;
import com.leyou.page.client.CategoryClient;
import com.leyou.page.client.GoodsClient;
import com.leyou.page.client.SpecificationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.page.service.impl
 * @ClassName: PageServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-05-10 6:49
 * @Version: 1.0
 */

@Slf4j
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public Map<String, Object> loadModel(Long spuId) {

        Map<String, Object> model = new HashMap<>();

        //查询spu
        Spu spu = goodsClient.querySpuById(spuId);

        //查询sku
        List<Sku> skus = spu.getSkus();

        //查询详情
        SpuDetail detail = spu.getSpuDetail();

        //查询brand
        Brand brand = brandClient.queryBrandById(spu.getBrandId());

        //查询商品分类
        List<Category> categories = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));

        //查询规格参数
        List<SpecGroup> specs = specClient.queryListByCid(spu.getCid3());

        model.put("spu", spu);
        model.put("skus", skus);
        model.put("detail", detail);
        model.put("brand", brand);
        model.put("specs", specs);
        model.put("categories", categories);

        return model;
    }

    @Override
    public void creatHtml(Long spuId) {

        //上下文
        Context context = new Context();
        context.setVariables(loadModel(spuId));

        //输出流
        File dest = new File("E:\\upload", spuId + ".html");

        //判断是否存在，存在就删除
        if (dest.exists()){
            dest.delete();
        }

        try {
            PrintWriter writer = new PrintWriter(dest, "UTF-8");

            //生成html
            templateEngine.process("item", context, writer);

        }catch (Exception e){
            log.error("[静态页服务] 生成静态页异常", e);
        }


    }

    @Override
    public void deleteHtml(Long spuId) {

        //输出流
        File dest = new File("E:\\upload", spuId + ".html");

        //判断是否存在，存在就删除
        if (dest.exists()){
            dest.delete();
        }

    }


}
