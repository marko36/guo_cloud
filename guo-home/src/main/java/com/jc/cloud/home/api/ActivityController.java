package com.jc.cloud.home.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Activity;
import com.jc.cloud.home.service.IActivityService;
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
 * 活动 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Api("活动信息管理")
@RestController
@RequestMapping("/v1/activity")
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @ApiOperation("根据用户Id查询活动")
    @GetMapping("/getByActivityUserId")
    public ObjectRestResponse<Activity> getByActivityUserId() {
        return new ObjectRestResponse<>().ok(activityService.getByUserId(BaseContextHandler.getUserId()));
    }

    @ApiOperation("发布活动")
    @PostMapping(value = "/save")
    public ObjectRestResponse<String> saveActivity(Activity activity) {
        try {
            ValidatorUtils.validateEntity(activity);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        activity.setUid(BaseContextHandler.getUserId());
        activity.setBabyId(BaseContextHandler.getBabyId());
        activityService.save(activity);
        return new ObjectRestResponse<>().ok("发布成功");
    }

    @ApiOperation("根据状态获取活动通知列表")
    @GetMapping("/getByStatus")
    public ObjectRestResponse<List<Activity>> getActivityStatus(Integer status) {
        return new ObjectRestResponse<>().ok(activityService.getActivityStatus(status));
    }

    @ApiOperation("获取活动详情")
    @GetMapping("/info")
    public ObjectRestResponse<Activity> activityDetails(String id) {
        return new ObjectRestResponse<>().ok(activityService.getById(id));
    }

    @ApiOperation("根据班级获取班级活动通知列表")
    @GetMapping("/getByClass")
    public ObjectRestResponse<List<Activity>> getByClassId(String classId) {
        return new ObjectRestResponse<>().ok(activityService.getByClassId(classId));
    }


    @ApiOperation("审核活动")
    @PostMapping(value = "/auditActivity")
    public ObjectRestResponse<String> auditActivity(String id, Integer status) {
        activityService.auditActivity(id, status);
        return new ObjectRestResponse<>().ok("审核活动成功");
    }
}

