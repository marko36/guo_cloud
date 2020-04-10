package com.jc.cloud.home.mapper;

import com.jc.cloud.home.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    // 获取本周的课程表
    @Select("SELECT * FROM sch_course WHERE YEARWEEK(date_format(week,'%Y-%m-%d')) = YEARWEEK(now())")
    List<Course> weeks();

    // 获取上一周的课程表
    @Select("SELECT * FROM sch_course WHERE YEARWEEK(date_format(week,'%Y-%m-%d')) = YEARWEEK(now())-1")
    List<Course> lastWeek();

    // 获取下一周的课程表
    @Select("SELECT * FROM sch_course WHERE YEARWEEK(date_format(week,'%Y-%m-%d')) = YEARWEEK(now())+1")
    List<Course> lowerWeek();
}
