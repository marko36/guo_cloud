package com.jc.cloud.shop.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.TreeUtil;
import com.jc.cloud.shop.entity.Catagory;
import com.jc.cloud.shop.service.ICatagoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@RestController
@RequestMapping("/v1/catagory")
public class CatagoryController {

    private static Logger log = LoggerFactory.getLogger(CatagoryController.class);

    @Autowired
    ICatagoryService catagoryService;

    @ApiOperation(value = "获取全部分类信息")
    @GetMapping("/list")
    public ObjectRestResponse<List<Catagory>> list(){
        List<Catagory> childTreeNodes = catagoryService.listAll();
        log.info("ok");
        return new ObjectRestResponse<List<Catagory>>().ok(TreeUtil.bulid(childTreeNodes,0));
    }

    @ApiOperation(value = "创建分类")
    @PostMapping("/addCatagory")
    public ObjectRestResponse<Catagory> addCatagory(Catagory catagory){
       return catagoryService.addCatagory(catagory);
    }

    @ApiOperation(value = "删除分类")
    @PostMapping("/delete")
    public ObjectRestResponse<Catagory> delete(Integer Id){
        return catagoryService.delete(Id);
    }

    @ApiOperation(value = "更新分类",notes = "根据商品分类信息进行更新")
    @PostMapping(value = "/updateCatagory")
    public ObjectRestResponse<Catagory> updateCatagory(Catagory catagory){
        log.info("ok");
        return catagoryService.updateCatagory(catagory);
    }
}


