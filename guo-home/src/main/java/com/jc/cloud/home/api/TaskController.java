package com.jc.cloud.home.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Task;
import com.jc.cloud.home.service.ITaskService;
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
 * 任务 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Api("任务信息管理")
@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @ApiOperation("查询任务")
    @GetMapping(value = "/list")
    public ObjectRestResponse<List<Task>> getAll() {
        return new ObjectRestResponse<>().ok(taskService.list());
    }

    @ApiOperation("根据userId查询任务")
    @GetMapping("/getUserTask")
    public ObjectRestResponse<Task> getByTaskUserId() {
        return new ObjectRestResponse<>().ok(taskService.getByTaskUserId(BaseContextHandler.getUserId()));
    }

    @ApiOperation("根据状态获取任务")
    @GetMapping("/getByStatusTask")
    public ObjectRestResponse<List<Task>> selTaskStatus(Integer status) {
        return new ObjectRestResponse<>().ok(taskService.selTaskStatus(status));
    }

    @ApiOperation("任务发布")
    @PostMapping(value = "/save")
    public ObjectRestResponse<String> save(Task task) {
        try {
            ValidatorUtils.validateEntity(task);
        } catch (Exception e) {
            return new ObjectRestResponse<>().ok(e.getMessage());
        }
        task.setUid(BaseContextHandler.getUserId());
        taskService.save(task);
        return new ObjectRestResponse<String>().ok("任务发布成功");
    }

    @ApiOperation("任务审核")
    @PostMapping(value = "/audit")
    public ObjectRestResponse<String> audit(String id,Integer status) {
        taskService.audit(id,status);
        return new ObjectRestResponse<String>().ok("任务审核成功");
    }


}

