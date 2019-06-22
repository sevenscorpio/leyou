package com.leyou.item.web;

import com.leyou.item.service.SpecificationService;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.item.web
 * @ClassName: SpecificationController
 * @Author:
 * @Description:
 * @Date: 2019-04-23 10:45
 * @Version: 1.0
 */

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据分类id查询规格组
     * @param cid
     * @Return: org.springframework.http.ResponseEntity<java.util.List<SpecGroup>>
     * @Author:
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(specificationService.queryGroupByCid(cid));
    }

    /**
     * 根据请求参数查询规格信息
     * @param gid
     * @param cid
     * @param searching
     * @Return: org.springframework.http.ResponseEntity<java.util.List<SpecParam>>
     * @Author:
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamList(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching
            ){
        return ResponseEntity.ok(specificationService.queryParamList(gid,cid,searching));
    }

    /**
     * 根据分类查询规格组及组内参数
     * @param cid
     * @Return: org.springframework.http.ResponseEntity<java.util.List<SpecGroup>>
     * @Author:
     */
    @GetMapping("group")
    public ResponseEntity<List<SpecGroup>> queryListByCid(@RequestParam("cid") Long cid){
        return ResponseEntity.ok(specificationService.queryListByCid(cid));
    }
}
