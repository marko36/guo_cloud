package com.jc.cloud.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Activity;
import com.jc.cloud.home.mapper.ActivityMapper;
import com.jc.cloud.home.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 活动 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ObjectRestResponse<Activity> getByUserId(String uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        Activity activity = getOne(queryWrapper);
        if (activity == null) {
            return new ObjectRestResponse<>().error("用户名不存在！");
        }
        return new ObjectRestResponse<>().ok(activity);
    }

    @Override
    public List<Activity> getActivityStatus(Integer status) {
        //构建查询参数
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", status);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public ObjectRestResponse<List<Activity>> getByClassId(String classId) {
        List<Activity> activities = activityMapper.activiList(classId);
        if (activities == null) {
            return new ObjectRestResponse<>().error("班级不存在！");
        }
        return new ObjectRestResponse<>().ok(activities);
    }

    @Override
    public void auditActivity(String id, Integer status) {
        Activity activity = new Activity();
        activity.setId(id);
        activity.setStatus(status);
        baseMapper.updateById(activity);
    }

}
