package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Monitor;
import com.jc.cloud.home.service.IMonitorService;
import com.jc.cloud.vo.home.MonitorVo;
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
 * 监控设备 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-14
 */
@Api("监控设备信息管理")
@RestController
@RequestMapping("/v1/monitor")
public class MonitorController {

    @Autowired
    private IMonitorService monitorService;

    @ApiOperation("获取监控列表")
    @GetMapping("/list")
    public ObjectRestResponse<List<MonitorVo>> getAll() {
        return new ObjectRestResponse<>().ok(monitorService.list());
    }

    @ApiOperation("添加监控设备")
    @PostMapping("/save")
    public ObjectRestResponse<String> save(Monitor monitor) {
        try {
            ValidatorUtils.validateEntity(monitor);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        monitorService.save(monitor);
        return new ObjectRestResponse<>().ok("添加成功");
    }

    @ApiOperation("更新监控设备")
    @PostMapping("/update")
    public ObjectRestResponse<String> update(Monitor monitor) {
        monitorService.updateById(monitor);
        return new ObjectRestResponse<>().ok("更新成功");
    }

    @ApiOperation("设备异常上报")
    @PostMapping("/abnormalReport")
    public ObjectRestResponse<String> abnormalReport(int status) {
        monitorService.abnormalReport(status);
        return new ObjectRestResponse<>().ok("设备异常上报成功");
    }

    @ApiOperation("设备维修")
    @PostMapping("/repair")
    public ObjectRestResponse<String> repair(int status) {
        monitorService.repair(status);
        return new ObjectRestResponse<>().ok("设备维修成功");
    }

}

