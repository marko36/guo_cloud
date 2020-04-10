package com.jc.cloud.home.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
public interface ISchoolService extends IService<School> {

    //查询未审核的学校信息
    List<School> getAll(boolean activity);

    //根据userId查询学校
    ObjectRestResponse<School> getByUserId(String uid);

    //学校审核
    void audit(String id, boolean activity);
}
