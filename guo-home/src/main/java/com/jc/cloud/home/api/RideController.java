package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Ride;
import com.jc.cloud.home.service.IRideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 乘座 前端控制器
 * </p>
 *
 * @author liuqing
 * @since 2019-06-04
 */
@Api("乘座信息管理")
@RestController
@RequestMapping("/v1/ride")
public class RideController {

    @Autowired
    private IRideService rideService;

    @ApiOperation("/乘座校车")
    @PostMapping("/save")
    public ObjectRestResponse<String> save(Ride ride) {
        try {
            ValidatorUtils.validateEntity(ride);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        rideService.save(ride);
        return new ObjectRestResponse<>().ok();
    }

    @ApiOperation("获取乘座人员详情")
    @GetMapping("/info")
    public ObjectRestResponse<Ride> personDetail(String id) {
        return new ObjectRestResponse<>().ok(rideService.personDetail(id));
    }
}