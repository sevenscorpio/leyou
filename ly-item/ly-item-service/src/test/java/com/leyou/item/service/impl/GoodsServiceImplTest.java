package com.leyou.item.service.impl;


import com.leyou.common.dto.CartDTO;
import com.leyou.item.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service.impl
 * @ClassName: GoodsServiceImplTest
 * @Author:
 * @Description:
 * @Date: 2019-05-28 1:45
 * @Version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void decreaseStock() {

        List<CartDTO> cartDTOS = Arrays.asList(new CartDTO(2600242L, 2),new CartDTO(2600248L, 2));

        goodsService.decreaseStock(cartDTOS);
    }
}