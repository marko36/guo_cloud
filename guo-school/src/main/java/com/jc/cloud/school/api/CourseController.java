package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.Course;
import com.jc.cloud.school.service.ICourseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程系列关联的视频 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/v1/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    @ApiOperation(value = "根据Id获取课程")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse<Course> getById(Integer id){
        return courseService.getCourseById(id);
    }

    @ApiOperation(value = "根据课程组合Id获取课程集合")
    @ApiImplicitParam(name = "combinationId",value = "课程组合id",required = true,paramType = "query")
    @GetMapping("/getListByCombinationId")
    public ObjectRestResponse<List<Course>> getListByCombinationId(Integer combinationId){
        return courseService.getByCombinationId(combinationId);
    }

    @ApiOperation(value = "创建新的课程")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "combinationId",value = "课程组合编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "课程名称",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面图片",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "presentation",value = "介绍图片",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "price",value = "价格",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "sequence",value = "排序编号",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(Course course){
        try {
            ValidatorUtils.validateEntity(course);
        } catch (Exception e) {
            return new ObjectRestResponse<Course>().error(e.getMessage());
        }

        course.setCreateBy(BaseContextHandler.getUserId());
        course.setCreateTime(LocalDateTime.now());
        return courseService.saveCourse(course);
    }

    @ApiOperation(value = "删除指定Id的课程")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return courseService.delete(id);
    }

    @ApiOperation(value = "删除多个课程")
    @ApiImplicitParam(name = "ids",value = "课程id编号集合",required = true,paramType = "query")
    @PostMapping("/deleteList")
    public ObjectRestResponse deleteList(List<Integer> ids){
        return courseService.deleteList(ids);
    }


    @ApiOperation(value = "更新指定课程")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "课程名称",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "presentation",value = "介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "price",value = "价格",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "sequence",value = "排序编号",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(Course course){
        course.setUpdateBy(BaseContextHandler.getUserId());
        course.setUpdateTime(LocalDateTime.now());
        return courseService.updateCourse(course);
    }

    @ApiOperation(value = "审核指定课程")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(Course course){
        course.setUpdateBy(BaseContextHandler.getUserId());
        course.setUpdateTime(LocalDateTime.now());
        return courseService.editStatus(course);
    }
}

