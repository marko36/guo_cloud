package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.RoutePlan;
import com.jc.cloud.home.service.IRoutePlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 路线规划 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/routePlan")
public class RoutePlanController {

    @Autowired
    private IRoutePlanService routePlanService;

    @ApiOperation("查看路线规划")
    @GetMapping("/list")
    public ObjectRestResponse<List<RoutePlan>> getAll(){
        return new ObjectRestResponse<>().ok(routePlanService.list());
    }

}

