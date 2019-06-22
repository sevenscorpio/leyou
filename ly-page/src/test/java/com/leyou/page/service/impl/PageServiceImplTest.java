package com.leyou.page.service.impl;

import com.leyou.page.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.page.service.impl
 * @ClassName: PageServiceImplTest
 * @Author:
 * @Description:
 * @Date: 2019-05-12 3:35
 * @Version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageServiceImplTest {

    @Autowired
    private PageService pageService;

    @Test
    public void creatHtml() {

        pageService.creatHtml(141L);
    }
}