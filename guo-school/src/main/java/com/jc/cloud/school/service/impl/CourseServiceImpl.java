package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Course;
import com.jc.cloud.school.entity.CourseSection;
import com.jc.cloud.school.mapper.CourseMapper;
import com.jc.cloud.school.service.ICourseSectionService;
import com.jc.cloud.school.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程系列关联的视频 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {


    @Autowired
    ICourseSectionService sectionService;

    @Override
    public ObjectRestResponse<List<Course>> getByCombinationId(Integer combinationId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        //queryWrapper.select("id,combinationId,name,cover,presentationUrl,resourceUrl,price,sequence,buyCount");
        queryWrapper.eq("combinationId",combinationId);
        return new ObjectRestResponse<List<Course>>().ok(this.list(queryWrapper));
    }

    @Override
    public ObjectRestResponse<Course> getCourseById(Integer id) {
        ObjectRestResponse<Course> response = new ObjectRestResponse<Course>();
        Course course = this.getById(id);
        return course == null ? response.error("没有找到这个课程的章节") : response.ok(course);
    }

    @Override
    public ObjectRestResponse saveCourse(Course course) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(course) ? response.ok("成功") : response.error("失败");
    }

    @Override
    public ObjectRestResponse<Course> updateCourse(Course course) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(course) ? response.ok("成功") : response.error("失败");
    }

    @Override
    public ObjectRestResponse<Course> editStatus(Course course) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(course) ? response.ok("成功") : response.error("失败");
    }

    @Override
    public ObjectRestResponse<Course> delete(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();

        Course course = this.getById(id);
        if(course == null){
           return response.error("没有这个课程");
        }

        List<CourseSection> sectionList = sectionService.getListByCourseId(id).getData();
        ObjectRestResponse<List<CourseSection>> courseResponse = null;
        if( sectionList.size() > 0 ){//如果课程底下有视频的话需要删除掉
            courseResponse = sectionService.deleteByCourseIds(new ArrayList<>(id));
        }

        if(courseResponse != null && courseResponse.isSuccess() && this.removeById(id)){
            return new ObjectRestResponse<Course>().ok("课程删除成功");
        }
        return new ObjectRestResponse<Course>().error("课程删除失败");
    }

    @Override
    public ObjectRestResponse<List<Course>> deleteList(List<Integer> ids) {
        ObjectRestResponse response = new ObjectRestResponse();
        ObjectRestResponse<List<CourseSection>> sectionResponse = sectionService.deleteByCourseIds(ids);

        //返回的不为空且判断值为true,就根据id集合进行删除，返回true即删除成功
        if(sectionResponse != null && sectionResponse.isSuccess() && this.removeByIds(ids) ){
            return response.ok("删除成功");
        }

        return response.ok("删除失败");
    }
}
