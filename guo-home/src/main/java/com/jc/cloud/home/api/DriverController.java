package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Driver;
import com.jc.cloud.home.service.IDriverService;
import com.jc.cloud.vo.home.DriverVo;
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
 * 司机 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-09
 */
@Api("司机管理信息")
@RestController
@RequestMapping("/v1/driver")
public class DriverController {

    /*@Autowired private IDriverService driverService;

    @ApiOperation("司机列表")
    @GetMapping("/list")
    public ObjectRestResponse<List<DriverVo>> getAll(){
        return new ObjectRestResponse<>().ok(driverService.list());
    }

    @ApiOperation("新增司机")
    @PostMapping("/save")
    public ObjectRestResponse<Object> saveDriver(Driver driver){
        try{
            ValidatorUtils.validateEntity(driver);
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        driverService.save(driver);
        return new ObjectRestResponse<>().ok("添加成功");
    }*/
}

