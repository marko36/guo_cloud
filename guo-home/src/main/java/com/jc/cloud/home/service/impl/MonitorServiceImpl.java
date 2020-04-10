package com.jc.cloud.home.service.impl;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Monitor;
import com.jc.cloud.home.mapper.MonitorMapper;
import com.jc.cloud.home.service.IMonitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 监控设备 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-14
 */
@Service
public class MonitorServiceImpl extends ServiceImpl<MonitorMapper, Monitor> implements IMonitorService {

    @Override
    public ObjectRestResponse<Object> abnormalReport(int status) {
        Monitor monitor = new Monitor();
        monitor.setStatus(status);
        baseMapper.updateById(monitor);
        return new ObjectRestResponse<>().ok();
    }

    @Override
    public ObjectRestResponse<Object> repair(int status) {
        Monitor monitor = new Monitor();
        monitor.setStatus(status);
        baseMapper.updateById(monitor);
        return new ObjectRestResponse<>().ok();
    }
}
