package com.jc.cloud.basic.api;


import com.jc.cloud.basic.entity.Broadcast;
import com.jc.cloud.basic.service.IBroadcastService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 如意家园轮播图 前端控制器
 * </p>
 *
 * @author lgh
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/v1/broadcast")
public class BroadcastController {

    @Autowired
    IBroadcastService broadcastService;

    @ApiOperation(value = "添加轮播图")
    @PostMapping("/save")
    public ObjectRestResponse<Object> save(Broadcast broadcast){
           return broadcastService.saveBroadcast(broadcast);
    }

    @ApiOperation(value = "更新轮播图")
    @PostMapping("/update")
    public ObjectRestResponse<Object> update(Broadcast broadcast){
        return broadcastService.updByBroadcast(broadcast);
    }

    @ApiOperation(value = "删除轮播图")
    @PostMapping("/delete")
    public ObjectRestResponse<Object> delete(String id){
        broadcastService.removeById(id);
        return new ObjectRestResponse<>().ok("删除成功");
    }

    @ApiOperation(value = "批量删除轮播图")
    @GetMapping("/delByIds")
    public ObjectRestResponse<Object> delByIds(String ids){
        broadcastService.delByIds(ids);
        return new ObjectRestResponse<>().ok("删除成功");
    }

    @ApiOperation(value = "获取所有轮播图")
    @GetMapping("/getAll")
    public ObjectRestResponse<Object> getAll(){
        return new ObjectRestResponse<>().data(broadcastService.list());
    }

    @ApiOperation(value = "获取单个轮播图信息")
    @GetMapping("/getById")
    public ObjectRestResponse<Object> getById(Integer id){
        return new ObjectRestResponse<>().data(broadcastService.getById(id));
    }

}

