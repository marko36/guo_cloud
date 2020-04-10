package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.CourseSection;

import java.util.List;

/**
 * <p>
 * 课程章节 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface ICourseSectionService extends IService<CourseSection> {

    ObjectRestResponse<CourseSection> getCourseSectionById(Integer id);

    ObjectRestResponse<List<CourseSection>> getListByCourseId(Integer courseId);

    ObjectRestResponse saveCourseSection(CourseSection courseSection);

    ObjectRestResponse updateCourseSection(CourseSection courseSection);

    ObjectRestResponse editStatus(CourseSection courseSection);

    ObjectRestResponse delete(Integer id);

    ObjectRestResponse deleteList(List<Integer> ids);

    ObjectRestResponse deleteByCourseIds(List<Integer> courseIds);
}
