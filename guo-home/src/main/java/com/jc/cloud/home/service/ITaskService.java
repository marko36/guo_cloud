package com.jc.cloud.home.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 任务 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
public interface ITaskService extends IService<Task> {

    //根据userId查询任务
    ObjectRestResponse<Task> getByTaskUserId(String uid);

    //根据状态获取任务
    List<Task> selTaskStatus(Integer status);

    //任务审核
    void audit(String id, Integer status);
}
