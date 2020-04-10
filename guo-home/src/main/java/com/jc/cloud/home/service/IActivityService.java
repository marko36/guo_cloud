package com.jc.cloud.home.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 活动 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
public interface IActivityService extends IService<Activity> {

    //根据用户Id查询活动
    ObjectRestResponse<Activity> getByUserId(String uid);

    // 根据状态获取活动通知列表
    List<Activity> getActivityStatus(Integer status);

    //根据班级获取班级活动通知列表
    ObjectRestResponse<List<Activity>> getByClassId(String classId);

    //审核活动
    void auditActivity(String id, Integer status);

}
