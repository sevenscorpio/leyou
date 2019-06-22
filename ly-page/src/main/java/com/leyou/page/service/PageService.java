package com.leyou.page.service;

import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.page.service
 * @ClassName: PageService
 * @Author:
 * @Description:
 * @Date: 2019-05-10 6:48
 * @Version: 1.0
 */
public interface PageService {

    public Map<String, Object> loadModel(Long spuId);

    public void creatHtml(Long spuId);

    public void deleteHtml(Long spuId);
}
