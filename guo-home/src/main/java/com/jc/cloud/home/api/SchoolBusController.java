package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.SchoolBus;
import com.jc.cloud.home.service.ISchoolBusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 校车 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-18
 */
@Api("校车信息管理")
@RestController
@RequestMapping("/v1/schoolBus")
public class SchoolBusController {

    @Autowired
    private ISchoolBusService schoolBusService;

    @ApiOperation("校车列表")
    @GetMapping("/list")
    public ObjectRestResponse<List<SchoolBus>> getAll() {
        return new ObjectRestResponse<>().ok(schoolBusService.list());
    }

//    @ApiOperation("创建校车")
//    @PostMapping("/save")
//    public ObjectRestResponse<Object> save(SchoolBus schoolBus) {
//        try {
//            ValidatorUtils.validateEntity(schoolBus);
//        } catch (Exception e) {
//            return new ObjectRestResponse<>().error(e.getMessage());
//        }
//        schoolBusService.save(schoolBus);
//        return new ObjectRestResponse<>().ok("创建成功");
//    }

    @ApiOperation("分配校车给司机")
    @PostMapping("/update")
    public ObjectRestResponse<String> update(SchoolBus schoolBus) {
        schoolBusService.updateById(schoolBus);
        return new ObjectRestResponse<>().ok();
    }

    @ApiOperation("/删除校车")
    @PostMapping("/delete")
    public ObjectRestResponse<String> delete(String id) {
        schoolBusService.removeById(id);
        return new ObjectRestResponse<>().ok("删除成功");
    }

    @ApiOperation("获取校车详情")
    @GetMapping("/info")
    public ObjectRestResponse<SchoolBus> busDetail(String id) {
        return new ObjectRestResponse<>().ok(schoolBusService.getById(id));
    }

    @ApiOperation("获取司机位置")
    @GetMapping("/getPosition")
    public ObjectRestResponse<SchoolBus> getPosition(String id) {
        return new ObjectRestResponse<>().ok(schoolBusService.getById(id));
    }

    @ApiOperation("校车审核")
    @PostMapping("/auditSchoolBus")
    public ObjectRestResponse<String> auditSchoolBus(String id,Integer workStatus){
        schoolBusService.auditSchoolBus(id,workStatus);
        return new ObjectRestResponse<>().ok("审核成功");
    }
}

