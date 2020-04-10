package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Course;

import java.util.List;

/**
 * <p>
 * 课程系列关联的视频 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface ICourseService extends IService<Course> {

    ObjectRestResponse<List<Course>> getByCombinationId(Integer combinationId);

    ObjectRestResponse<Course> getCourseById(Integer id);

    ObjectRestResponse saveCourse(Course course);

    ObjectRestResponse updateCourse(Course course);

    ObjectRestResponse editStatus(Course course);

    ObjectRestResponse delete(Integer id);

    ObjectRestResponse deleteList(List<Integer> ids);
}
