package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Course;
import com.jc.cloud.home.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Api("课程信息管理")
@RestController
@RequestMapping("/v1/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @ApiOperation("获取本周的课程表")
    @GetMapping("/weeks")
    public ObjectRestResponse<List<Course>> weeks() {
        return new ObjectRestResponse<>().ok(courseService.weeks());
    }

    @ApiOperation("获取上一周的课程表")
    @GetMapping("/lastWeek")
    public ObjectRestResponse<List<Course>> lastWeek() {
        return new ObjectRestResponse<>().ok(courseService.lastWeek());
    }

    @ApiOperation("获取下一周的课程表")
    @GetMapping("/lowerWeek")
    public ObjectRestResponse<List<Course>> lowerWeek() {
        return new ObjectRestResponse<>().ok(courseService.lowerWeek());
    }
}

