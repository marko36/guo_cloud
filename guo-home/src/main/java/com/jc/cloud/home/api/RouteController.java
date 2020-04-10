package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Route;
import com.jc.cloud.home.service.IRouteService;
import com.jc.cloud.vo.home.RouteVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 路线 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-09
 */
@Api("路线信息管理")
@RestController
@RequestMapping("/v1/route")
public class RouteController {

    @Autowired
    private IRouteService routeService;

    @ApiOperation("查看路线")
    @GetMapping("/list")
    public ObjectRestResponse<List<RouteVo>> getAll() {
        return new ObjectRestResponse<>().ok(routeService.list());
    }
}