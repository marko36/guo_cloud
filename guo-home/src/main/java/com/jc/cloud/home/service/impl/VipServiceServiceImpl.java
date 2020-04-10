package com.jc.cloud.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.home.entity.VipService;
import com.jc.cloud.home.mapper.VipServiceMapper;
import com.jc.cloud.home.service.IVipServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 支付 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-09
 */
@Service
public class VipServiceServiceImpl extends ServiceImpl<VipServiceMapper, VipService> implements IVipServiceService {

    @Override
    public List<VipService> getVipType(Integer type) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type",type);
        return list(queryWrapper);
    }
}
