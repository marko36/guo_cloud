package com.jc.cloud.home.service;

import com.jc.cloud.home.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
public interface ICourseService extends IService<Course> {

    // 获取本周的课程表
    List<Course> weeks();

    // 获取上一周的课程表
    List<Course> lastWeek();

    // 获取下一周的课程表
    List<Course> lowerWeek();
}
