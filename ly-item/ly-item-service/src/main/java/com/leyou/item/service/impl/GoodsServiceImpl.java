package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.dto.CartDTO;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.*;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.*;
import com.leyou.item.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service.impl
 * @ClassName: GoodsServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-04-23 17:32
 * @Version: 1.0
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public PageResult<Spu> querySpuPage(Integer page, Integer rows, Boolean saleable, String key) {

        //分页
        PageHelper.startPage(page, rows);

        //搜索


        //过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        //搜索
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title", "%" + key + "%");
        }

        //上下架查询
        if (saleable != null){
            criteria.andEqualTo("saleable",saleable);
        }

        //默认根据更新时间排序
        example.setOrderByClause("last_update_time DESC");

        //查询
        List<Spu> spus = spuMapper.selectByExample(example);

        //判断
        if (CollectionUtils.isEmpty(spus)){
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }

        //解析分类和品牌名称
        loadCategoryAndBrandName(spus);

        //解析分页结果
        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);
        return new PageResult<>(spuPageInfo.getTotal(), spus);
    }

    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus){

            //处理分类名称
            List<String> names = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names,"/"));

            //处理品牌名称
            spu.setBname(brandService.queryById(spu.getBrandId()).getName());
        }
    }

    @Override
    @Transactional
    public void addGoods(Spu spu) {

        //添加spu
        spu.setId(null);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setSaleable(true);
        spu.setValid(false);

        int count = spuMapper.insert(spu);
        if (count != 1){
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

        //添加detail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        count = spuDetailMapper.insert(spuDetail);
        if (count != 1){
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

        //添加sku和stock
        saveSkuAndStock(spu);

        //发送mq
        amqpTemplate.convertAndSend("item.insert", spu.getId());

    }

    /**
     * 添加sku和stock
     * @param spu
     * @Return: void
     * @Author:
     */
    public void saveSkuAndStock(Spu spu){
        //库存集合
        List<Stock> stocks = new ArrayList<>();

        int count;
        //添加sku
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus){
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());

            count = skuMapper.insert(sku);
            if (count != 1){
                throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());

            stocks.add(stock);
        }

        //库存批量新增
        count = stockMapper.insertList(stocks);
        if (count != stocks.size()){
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
    }

    @Override
    public SpuDetail queryDetailById(Long id) {

        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(id);

        if (spuDetail == null){
            throw new LyException(ExceptionEnum.GOODS_DETAIL_NOT_FOUND);
        }

        return spuDetail;
    }

    @Override
    public List<Sku> querySkuBySpuId(Long spuId) {

        Sku sku = new Sku();
        sku.setSpuId(spuId);

        List<Sku> skuList = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)){
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }

        //查询库存
        //多次查询多次请求
//        for(Sku s : skuList){
//            Stock stock = stockMapper.selectByPrimaryKey(s.getId());
//            if (stock == null){
//                throw new LyException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
//            }
//            s.setStock(stock.getStock());
//        }

        //一次查询
        List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        loadStockinSku(ids, skuList);

        return skuList;
    }

    @Override
    @Transactional
    public void updateGoods(Spu spu) {

        if (spu.getId() == null){
            throw new LyException(ExceptionEnum.GOODS_ID_CANNOT_BE_NULL);
        }

        Sku sku = new Sku();
        sku.setSpuId(spu.getId());

        //查询sku
        List<Sku> skuList = skuMapper.select(sku);

        if (!CollectionUtils.isEmpty(skuList)){

            //删除sku
            skuMapper.delete(sku);

            //删除stock
            List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            stockMapper.deleteByIdList(ids);

        }

        //修改spu
        spu.setValid(null);
        spu.setSaleable(null);
        spu.setLastUpdateTime(new Date());

        int count = spuMapper.updateByPrimaryKeySelective(spu);
        if (count != 1){
            throw new LyException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }

        //修改detail
        count = spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
        if (count != 1){
            throw new LyException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }

        //新增sku，新增stock
        saveSkuAndStock(spu);

        //发送mq
        amqpTemplate.convertAndSend("item.update", spu.getId());
    }

    @Override
    public Spu querySpuById(Long id) {

        // 查询spu
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null){
            throw new LyException(ExceptionEnum.GOODS_SPU_NOT_FOUND);
        }

        // 查询sku
        spu.setSkus(querySkuBySpuId(id));

        // 查询detail
        spu.setSpuDetail(queryDetailById(id));

        return spu;
    }

    @Override
    public List<Sku> querySkuByIds(List<Long> ids) {

        List<Sku> skuList = skuMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(skuList)){
            throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }
        loadStockinSku(ids, skuList);

        return skuList;
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOS) {

        for (CartDTO cartDTO : cartDTOS) {

            // 减库存
            int count = stockMapper.decreaseStock(cartDTO.getSkuId(), cartDTO.getNum());
            if (count != 1){
                throw new LyException(ExceptionEnum.STOCK_NOT_ENOUGH);
            }
        }
    }

    private void loadStockinSku(List<Long> ids, List<Sku> skuList) {
        //一次查询
        List<Stock> stockList = stockMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(stockList)) {
            throw new LyException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
        }

        //把stock变成一个map，key是sku的id，值是库存值
        Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        skuList.forEach(s -> s.setStock(stockMap.get(s.getId())));
    }

}
