package com.jc.cloud.school.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.CourseCombination;
import com.jc.cloud.school.service.ICourseCombinationService;
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
 * 课程组合 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/v1/courseCombination")
public class CourseCombinationController {

    @Autowired
    ICourseCombinationService combinationService;

    @ApiOperation(value = "根据Id获取课程组合")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse<CourseCombination> getById(Integer id){
        return combinationService.getCourseCombination(id);
    }

    @ApiOperation(value = "获取课程组合集合,查询所有")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageNumber",value = "页码，第几页",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "limit",value = "一页要几条信息",required = true,paramType = "query")
            }
    )
    @GetMapping("/getList")
    public ObjectRestResponse<List<CourseCombination>> getList(Integer pageNumber,Integer limit){
        return combinationService.getCourseCombinationList(new Page<CourseCombination>(pageNumber,limit));
    }

    @ApiOperation(value = "创建新的课程组合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "名称标题",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "presentationUrl",value = "介绍图片",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面图片",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(CourseCombination courseCombination){
        try {
            ValidatorUtils.validateEntity(courseCombination);
        } catch (Exception e) {
            return new ObjectRestResponse<CourseCombination>().error(e.getMessage());
        }

        courseCombination.setCreateBy(courseCombination.getCreateBy());
        courseCombination.setCreateTime(LocalDateTime.now());
        return combinationService.saveCourseCombination(courseCombination);
    }

    @ApiOperation(value = "更新指定课程组合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "名称标题",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "presentationUrl",value = "介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面图片",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(CourseCombination courseCombination){
        courseCombination.setUpdateBy(BaseContextHandler.getUserId());
        courseCombination.setUpdateTime(LocalDateTime.now());
        return combinationService.updateCourseCombination(courseCombination);
    }

    @ApiOperation(value = "删除指定Id的课程组合")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return combinationService.delete(id);
    }

    @ApiOperation(value = "删除指定Id的课程组合集合")
    @ApiImplicitParam(name = "ids",value = "课程组合id编号集合",required = true,paramType = "query")
    @PostMapping("/deleteList")
    public ObjectRestResponse deleteList(List<Integer> ids){
        return combinationService.deleteList(ids);
    }

    @ApiOperation(value = "审核指定课程组合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(CourseCombination courseCombination){
        courseCombination.setUpdateBy(BaseContextHandler.getUserId());
        courseCombination.setUpdateTime(LocalDateTime.now());
        return combinationService.editStatus(courseCombination);
    }

}

