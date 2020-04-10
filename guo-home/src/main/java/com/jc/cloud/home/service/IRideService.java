package com.jc.cloud.home.service;

import com.jc.cloud.home.entity.Ride;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 乘座 服务类
 * </p>
 *
 * @author liuqing
 * @since 2019-06-04
 */
public interface IRideService extends IService<Ride> {
    //获取乘座人员详情
    Ride personDetail(String id);
}
