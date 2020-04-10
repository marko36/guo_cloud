package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.CourseSection;
import com.jc.cloud.school.mapper.CourseSectionMapper;
import com.jc.cloud.school.service.ICourseSectionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程章节 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Service
public class CourseSectionServiceImpl extends ServiceImpl<CourseSectionMapper, CourseSection> implements ICourseSectionService {

    @Override
    public ObjectRestResponse<CourseSection> getCourseSectionById(Integer id) {
        ObjectRestResponse<CourseSection> response = new ObjectRestResponse();
        CourseSection courseSection = this.getById(id);
        return courseSection == null ? response.error("没找到这个章节") : response.ok(courseSection);
    }

    @Override
    public ObjectRestResponse<List<CourseSection>> getListByCourseId(Integer courseId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //queryWrapper.select("id,name,courseId,videoUrl,sequence,time")
        queryWrapper.eq("courseId",courseId);
        return new ObjectRestResponse<List<CourseSection>>().ok(this.list(queryWrapper));
    }

    @Override
    public ObjectRestResponse saveCourseSection(CourseSection courseSection) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(courseSection) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateCourseSection(CourseSection courseSection) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(courseSection) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse editStatus(CourseSection courseSection) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(courseSection) ? response.ok("审核保存成功") : response.error("审核保存失败");
    }

    @Override
    public ObjectRestResponse delete(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }

    @Override
    public ObjectRestResponse deleteList(List<Integer> ids) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeByIds(ids) ? response.ok("删除成功") : response.error("删除失败");
    }

    @Override
    public ObjectRestResponse deleteByCourseIds(List<Integer> courseIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("courseId",courseIds);
        ObjectRestResponse response = new ObjectRestResponse();
        return this.remove(queryWrapper) ? response.ok("删除成功") : response.error("删除失败");
    }


}
