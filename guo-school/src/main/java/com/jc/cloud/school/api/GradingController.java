package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.Grading;
import com.jc.cloud.school.service.IGradingService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * <p>
 * 分级 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/grading")
public class GradingController {

    @Autowired
    IGradingService gradingService;

    @ApiOperation("获取分级")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse getById(Integer id){
        return gradingService.getGradingById(id);
    }

    @ApiOperation("获取全部分级")
    @GetMapping("/getListAll")
    public ObjectRestResponse getListAll(){
        return gradingService.getListAll();
    }


    @ApiOperation("增加保存分级")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "名称",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "ageGroup",value = "年龄段",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(Grading grading){

        try {
            ValidatorUtils.validateEntity(grading);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }
        grading.setCreateBy(BaseContextHandler.getUserId());
        grading.setCreateTime(LocalDateTime.now());
        return gradingService.saveGrading(grading);
    }

    @ApiOperation("修改分级")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "名称",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "ageGroup",value = "年龄段",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(Grading grading){
        grading.setUpdateBy(BaseContextHandler.getUserId());
        grading.setUpdateTime(LocalDateTime.now());
        return gradingService.updateGrading(grading);
    }

    @ApiOperation("删除分级")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return gradingService.deleteGrading(id);
    }


}

