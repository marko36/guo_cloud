package com.jc.cloud.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Baby;
import com.jc.cloud.home.mapper.BabyMapper;
import com.jc.cloud.home.service.IBabyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 宝宝 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Service
public class BabyServiceImpl extends ServiceImpl<BabyMapper, Baby> implements IBabyService {

    @Override
    public ObjectRestResponse<Baby> getActiviyBabyByUid(String uid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        queryWrapper.eq("activity", true);

        Baby baby = getOne(queryWrapper);
        if (baby == null) {
           return new ObjectRestResponse<>().error("用户名不存在！");
        }
        return new ObjectRestResponse<>().ok(baby);
    }

    @Override
    public void babyAudit(String id, Integer examine) {
        Baby baby = new Baby();
        baby.setId(id);
        baby.setExamine(examine);
        baseMapper.updateById(baby);
    }

    @Override
    public void switchActiviy(String id, Boolean activity) {
        Baby baby = new Baby();
        baby.setId(id);
        baby.setActivity(activity);
        baseMapper.updateById(baby);
    }
}
