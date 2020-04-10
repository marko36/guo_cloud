package com.jc.cloud.basic.service;

import com.jc.cloud.basic.entity.Broadcast;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;

/**
 * <p>
 * 如意家园轮播图 服务类
 * </p>
 *
 * @author lgh
 * @since 2019-05-31
 */
public interface IBroadcastService extends IService<Broadcast> {

    ObjectRestResponse<Object> saveBroadcast(Broadcast broadcast);

    ObjectRestResponse<Object> updByBroadcast(Broadcast broadcast);

    void delByIds(String ids);
}
