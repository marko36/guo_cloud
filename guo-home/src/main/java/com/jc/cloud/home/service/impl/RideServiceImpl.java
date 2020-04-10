package com.jc.cloud.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.home.entity.Ride;
import com.jc.cloud.home.mapper.RideMapper;
import com.jc.cloud.home.service.IRideService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 乘座 服务实现类
 * </p>
 *
 * @author liuqing
 * @since 2019-06-04
 */
@Service
public class RideServiceImpl extends ServiceImpl<RideMapper, Ride> implements IRideService {

    @Override
    public Ride personDetail(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        return baseMapper.selectById(id);
    }
}
