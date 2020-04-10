package com.jc.cloud.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.cloud.school.entity.CourseSection;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课程章节 Mapper 接口
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface CourseSectionMapper extends BaseMapper<CourseSection> {

    @Select("SELECT * FROM sxy_course_section WHERE courseId = #{0}")
    List<CourseSection> getListByCourseId(Integer courseId);
}
