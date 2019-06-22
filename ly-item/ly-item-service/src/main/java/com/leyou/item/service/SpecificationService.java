package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service
 * @ClassName: SpecificationService
 * @Author:
 * @Description:
 * @Date: 2019-04-23 10:42
 * @Version: 1.0
 */
public interface SpecificationService {
    public List<SpecGroup> queryGroupByCid(Long cid);

    public List<SpecParam> queryParamList(Long gid,Long cid,Boolean searching);

    public List<SpecGroup> queryListByCid(Long cid);
}
