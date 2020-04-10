package com.jc.cloud.home.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.MD5;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.MonitorConfig;
import com.jc.cloud.home.service.IMonitorConfigService;
import com.jc.cloud.vo.home.MonitorConfigVo;
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
 * 校园监控配置 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-05
 */
@Api("校园监控配置信息管理")
@RestController
@RequestMapping("/v1/monitorConfig")
public class MonitorConfigController {

    @Autowired
    private IMonitorConfigService monitorConfigService;

    @ApiOperation("获取所有监控配置信息")
    @GetMapping("/list")
    public ObjectRestResponse<List<MonitorConfigVo>> getAll() {
        return new ObjectRestResponse<>().ok(monitorConfigService.list());
    }

    @ApiOperation("新增监控配置信息")
    @PostMapping("/save")
    public ObjectRestResponse<String> save(MonitorConfig monitorConfig) {
        try {
            ValidatorUtils.validateEntity(monitorConfig);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        monitorConfig.setPassword(MD5.MD5Encode(monitorConfig.getPassword()));
        monitorConfig.setUserName(BaseContextHandler.getUserName());
        monitorConfigService.save(monitorConfig);
        return new ObjectRestResponse<>().ok("添加成功");
    }

    @ApiOperation("更新监控配置信息")
    @PostMapping("/update")
    public ObjectRestResponse<String> update(MonitorConfig monitorConfig) {
        monitorConfigService.updateById(monitorConfig);
        return new ObjectRestResponse<>().ok("更新成功");
    }
}