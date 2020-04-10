package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Label;
import com.jc.cloud.home.service.ILabelService;
import com.jc.cloud.vo.home.LabelVo;
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
 * 标签表 前端控制器
 * </p>
 *
 * @author liuqing
 * @since 2019-05-24
 */
@Api("标签信息管理")
@RestController
@RequestMapping("/v1/label")
public class LabelController {

    @Autowired
    private ILabelService labelService;

    @ApiOperation("获取任务标签")
    @GetMapping("/list")
    public ObjectRestResponse<List<LabelVo>> getAll() {
        return new ObjectRestResponse<>().ok(labelService.list());
    }

    @ApiOperation("创建任务标签")
    @PostMapping(value = "/save")
    public ObjectRestResponse<String> saveTaskLabel(Label label) {
        try {
            ValidatorUtils.validateEntity(label);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        labelService.save(label);
        return new ObjectRestResponse<>().ok("创建任务标签成功");
    }

    @ApiOperation("更新任务标签")
    @PostMapping(value = "/update")
    public ObjectRestResponse<String> updateTaskLabel(Label label) {
        labelService.updateById(label);
        return new ObjectRestResponse<>().ok("更新任务标签成功");
    }
}

