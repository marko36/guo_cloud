package com.jc.cloud.home.service;

import com.jc.cloud.home.entity.SchoolBus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 校车 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-18
 */
public interface ISchoolBusService extends IService<SchoolBus> {
    //校车审核
    void auditSchoolBus(String id, Integer workStatus);
}
