package com.jc.cloud.home.service.impl;

import com.jc.cloud.home.entity.Course;
import com.jc.cloud.home.mapper.CourseMapper;
import com.jc.cloud.home.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> weeks() {
        return courseMapper.weeks();
    }

    @Override
    public List<Course> lastWeek() {
        return courseMapper.lastWeek();
    }

    @Override
    public List<Course> lowerWeek() {
        return courseMapper.lowerWeek();
    }
}
