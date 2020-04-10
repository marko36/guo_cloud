package com.jc.cloud.home.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Baby;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 宝宝 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
public interface IBabyService extends IService<Baby> {

    //根据用户Id查询宝宝信息
    ObjectRestResponse<Baby> getActiviyBabyByUid(String uid);

    //宝宝审核通过
    void babyAudit(String id, Integer examine);

    //切换宝宝激活状态
    void switchActiviy(String id, Boolean activity);

}
