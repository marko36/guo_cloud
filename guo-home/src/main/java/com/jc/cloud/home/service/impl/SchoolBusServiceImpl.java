package com.jc.cloud.home.service.impl;

import com.jc.cloud.home.entity.SchoolBus;
import com.jc.cloud.home.mapper.SchoolBusMapper;
import com.jc.cloud.home.service.ISchoolBusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 校车 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-18
 */
@Service
public class SchoolBusServiceImpl extends ServiceImpl<SchoolBusMapper, SchoolBus> implements ISchoolBusService {

    @Override
    public void auditSchoolBus(String id, Integer workStatus) {
        SchoolBus schoolBus = new SchoolBus();
        schoolBus.setId(id);
        schoolBus.setWorkStatus(workStatus);
        baseMapper.updateById(schoolBus);
    }
}
