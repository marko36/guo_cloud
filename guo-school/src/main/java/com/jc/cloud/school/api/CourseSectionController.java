package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.CourseSection;
import com.jc.cloud.school.service.ICourseSectionService;
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
 * 课程章节 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/v1/courseSection")
public class CourseSectionController {

    //private static final Logger log = LoggerFactory.getLogger(CourseSectionController.class);

    @Autowired
    ICourseSectionService sectionService;


    @ApiOperation(value = "根据Id获取章节")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse<CourseSection> getCourseSectionById(Integer id){
        return sectionService.getCourseSectionById(id);
    }

    @ApiOperation(value = "根据课程Id获取章节集合")
    @ApiImplicitParam(name = "courseId",value = "课程id编号",required = true,paramType = "query")
    @GetMapping("/getListByCourseId")
    public ObjectRestResponse<List<CourseSection>> getListByCourseId(Integer courseId){
        return sectionService.getListByCourseId(courseId);
    }


    @ApiOperation(value = "创建新的章节")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "章节名",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "courseId",value = "依赖的课程编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "videoUrl",value = "视频地址",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "sequence",value = "排序编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "time",value = "播放时间长度",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(CourseSection courseSection){
        try {
            ValidatorUtils.validateEntity(courseSection);
        } catch (Exception e) {
            return new ObjectRestResponse<CourseSection>().error(e.getMessage());
        }

        courseSection.setCreateBy(BaseContextHandler.getUserId());
        courseSection.setCreateTime(LocalDateTime.now());
        return sectionService.saveCourseSection(courseSection);
    }

    @ApiOperation(value = "删除指定Id集合课程章节")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/deleteList")
    public ObjectRestResponse deleteList(List<Integer> ids){
        return  sectionService.deleteList(ids);
    }

    @ApiOperation(value = "删除指定Id课程章节")
    @ApiImplicitParam(name = "courseId",value = "课程Id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer courseId){
        return  sectionService.delete(courseId);
    }

    @ApiOperation(value = "更新指定章节")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "章节名",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "videoUrl",value = "视频地址",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "sequence",value = "排序编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "time",value = "播放时间长度",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(CourseSection courseSection){
        courseSection.setUpdateBy(BaseContextHandler.getUserId());
        courseSection.setUpdateTime(LocalDateTime.now());
        return sectionService.updateCourseSection(courseSection);
    }

    @ApiOperation(value = "审核指定章节")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(CourseSection courseSection){
        courseSection.setUpdateBy(BaseContextHandler.getUserId());
        courseSection.setUpdateTime(LocalDateTime.now());
        return sectionService.editStatus(courseSection);
    }
}

