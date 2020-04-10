package com.jc.cloud.home.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Monitor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 监控设备 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-14
 */
public interface IMonitorService extends IService<Monitor> {

    //设备异常上报
    ObjectRestResponse<Object> abnormalReport(int status);

    //设备维修
    ObjectRestResponse<Object> repair(int status);

}
