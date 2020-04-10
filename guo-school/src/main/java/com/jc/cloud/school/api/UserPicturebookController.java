package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.UserPicturebook;
import com.jc.cloud.school.service.IUserPicturebookService;
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
 * 用户和绘本 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/userPicturebook")
public class UserPicturebookController {

    @Autowired
    IUserPicturebookService userPicturebookService;

    @ApiOperation("获取用户使用绘本记录")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getUserPicturebookById")
    public ObjectRestResponse getUserPicturebookById(Integer id){
        return userPicturebookService.getUserPicturebookById(id);
    }

    @ApiOperation("根据绘本组合编号获取用户使用绘本集合")
    @ApiImplicitParam(name = "combinationId",value = "绘本id编号",required = true,paramType = "query")
    @GetMapping("/getListByCombinationId")
    public ObjectRestResponse getListByCombinationId(Integer combinationId){
        return userPicturebookService.getListByUserCombinationId(combinationId);
    }

    @ApiOperation("增加用户使用绘本组合记录")
    @PostMapping("/save")
    public ObjectRestResponse save(UserPicturebook userPicturebook){
        try {
            ValidatorUtils.validateEntity(userPicturebook);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }
        userPicturebook.setCreateBy(BaseContextHandler.getUserId());
        userPicturebook.setCreateTime(LocalDateTime.now());
        return userPicturebookService.saveUserPicturebook(userPicturebook);
    }

    @ApiOperation("修改用户使用绘本组合记录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "voiceFrequency",value = "音频地址",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "voiceFrequency",value = "分数",required = true,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(UserPicturebook userPicturebook){
        userPicturebook.setUpdateBy(BaseContextHandler.getUserId());
        userPicturebook.setUpdateTime(LocalDateTime.now());
        return userPicturebookService.updateUserPicturebook(userPicturebook);
    }

    @ApiOperation("删除用户使用绘本组合记录")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return userPicturebookService.deleteUserPicturebook(id);
    }
}

