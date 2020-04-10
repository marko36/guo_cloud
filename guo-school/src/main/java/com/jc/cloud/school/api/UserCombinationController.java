package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.UserCombination;
import com.jc.cloud.school.service.IUserCombinationService;
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
 * 用户和绘本组合 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/userCombination")
public class UserCombinationController {

    @Autowired
    IUserCombinationService userCombinationService;

    @ApiOperation("获取用户使用绘本组合集合")
    @GetMapping("/getListByUserId")
    public ObjectRestResponse getListByUserId(){
        return userCombinationService.getUserCombinationList(BaseContextHandler.getUserId());
    }

    @ApiOperation("增加用户使用绘本组合记录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "combinationId",value = "绘本组合编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "score",value = "总成绩",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(UserCombination userCombination){
        try {
            ValidatorUtils.validateEntity(userCombination);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }
        userCombination.setCreateBy(BaseContextHandler.getUserId());
        userCombination.setCreateTime(LocalDateTime.now());
        return userCombinationService.saveUserCombination(userCombination);
    }

    @ApiOperation("修改用户使用绘本组合记录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "TotalScore",value = "总成绩",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(UserCombination userCombination){
        userCombination.setUpdateBy(BaseContextHandler.getUserId());
        userCombination.setUpdateTime(LocalDateTime.now());
        return userCombinationService.updateUserCombination(userCombination);
    }

    @ApiOperation("删除用户使用绘本组合记录")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return userCombinationService.deleteUserCombination(id);
    }


}

