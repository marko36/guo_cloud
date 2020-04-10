package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Activity;

import java.util.List;

/**
 * <p>
 * 特训营 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface IActivityService extends IService<Activity> {

    ObjectRestResponse<Activity> getActivityById(Integer id);

    ObjectRestResponse<List<Activity>> getListBySymbols(Integer month, Page page);

    ObjectRestResponse saveActivity(Activity activity);

    ObjectRestResponse updateActivity(Activity activity);

    ObjectRestResponse editStatus(Activity activity);

    ObjectRestResponse delete(Integer id);


}
