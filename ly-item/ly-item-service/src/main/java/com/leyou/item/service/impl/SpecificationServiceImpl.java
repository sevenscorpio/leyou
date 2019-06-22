package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.service.impl
 * @ClassName: SpecificationServiceImpl
 * @Author:
 * @Description:
 * @Date: 2019-04-23 10:44
 * @Version: 1.0
 */

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> queryGroupByCid(Long cid) {

        //查询条件
        SpecGroup group = new SpecGroup();
        group.setCid(cid);

        List<SpecGroup> groups = specGroupMapper.select(group);

        //判断
        if (CollectionUtils.isEmpty(groups)){

            //没查到
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }

        return groups;
    }

    @Override
    public List<SpecParam> queryParamList(Long gid,Long cid,Boolean searching) {

        //查询条件
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(searching);

        //查询
        List<SpecParam> params = specParamMapper.select(param);

        //判断
        if(CollectionUtils.isEmpty(params)){

            throw new LyException(ExceptionEnum.PARAM_GROUP_NOT_FOUND);
        }

        return params;
    }

    @Override
    public List<SpecGroup> queryListByCid(Long cid) {

        //查询规格组
        List<SpecGroup> specGroups = queryGroupByCid(cid);

        //查询当前分类下的参数
        List<SpecParam> specParams = queryParamList(null, cid, null);

        //准备一个map，map的key是规格组的id，map的值是组下所有参数
        Map<Long, List<SpecParam>> map = new HashMap<>();
        for (SpecParam param : specParams) {
            if (!map.containsKey(param.getGroupId())){
                //这个组id在map中不存在，就新增一个list
                map.put(param.getGroupId(), new ArrayList<>());
            }
            map.get(param.getGroupId()).add(param);
        }

        //填充param到group
        for (SpecGroup specGroup : specGroups) {
            specGroup.setParams(map.get(specGroup.getId()));
        }

        return specGroups;
    }
}
