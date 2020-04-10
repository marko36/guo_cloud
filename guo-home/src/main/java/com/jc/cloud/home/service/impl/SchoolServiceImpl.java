package com.jc.cloud.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.School;
import com.jc.cloud.home.mapper.SchoolMapper;
import com.jc.cloud.home.service.ISchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

    @Override
    public List<School> getAll(boolean activity) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("activity", activity);
        return list(queryWrapper);
    }

    @Override
    public ObjectRestResponse<School> getByUserId(String uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid);
        queryWrapper.eq("activity", true);
        School school = getOne(queryWrapper);
        if (school == null) {
            return new ObjectRestResponse<>().error("用户名不存在！");
        }
        return new ObjectRestResponse<>().ok(school);
    }

    @Override
    public void audit(String id, boolean activity) {
        School school = new School();
        school.setId(id);
        school.setActivity(activity);
        baseMapper.updateById(school);
    }
}
