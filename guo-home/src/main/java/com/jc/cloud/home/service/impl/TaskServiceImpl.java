package com.jc.cloud.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Task;
import com.jc.cloud.home.mapper.TaskMapper;
import com.jc.cloud.home.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Override
    public ObjectRestResponse<Task> getByTaskUserId(String uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        Task task = getOne(queryWrapper);
        if (task == null) {
            return new ObjectRestResponse<>().error("用户名不存在！");
        }
        return new ObjectRestResponse<>().ok(task);
    }

    @Override
    public List<Task> selTaskStatus(Integer status) {
        //构建查询参数
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", status);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void audit(String id, Integer status) {
        Task task = new Task();
        task.setId(id);
        task.setStatus(status);
        baseMapper.updateById(task);
    }
}
