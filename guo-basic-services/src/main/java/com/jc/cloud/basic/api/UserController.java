package com.jc.cloud.basic.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.auth.client.annotation.IgnoreUserToken;
import com.jc.cloud.basic.entity.User;
import com.jc.cloud.basic.service.ICmsUserService;
import com.jc.cloud.basic.service.IUserService;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.enums.UserType;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.vo.auth.JwtAuthenticationRequest;
import com.jc.cloud.vo.auth.JwtAuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户接口
 * </p>
 *
 * @author fangliai
 * @since 2019-05-24
 */
@Api(value = "用户")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired private IUserService userService;
    @Autowired private ICmsUserService cmsUserService;

    /**
     * 授权中心调用接口
     * @param authenticationRequest
     * @return
     */
    @PostMapping("/login")
    @IgnoreUserToken
    private ObjectRestResponse<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest authenticationRequest){
        UserType userType=UserType.valueOf(authenticationRequest.getScope().toUpperCase());
        if(UserType.MANAGE.equals(userType)){
            return cmsUserService.login(authenticationRequest);
        }
        return userService.login(authenticationRequest);
    }

    @PostMapping("/register")
    @IgnoreUserToken
    public ObjectRestResponse<String> register(User user){
        return null;
    }


    @ApiOperation(value = "获取用户信息")
    @GetMapping("/info")
    public ObjectRestResponse<Object> getInfo(){
        return userService.getInfoById(BaseContextHandler.getUserId());
    }

    @ApiOperation(value = "添加积分")
    @PostMapping("/updateScore")
    public ObjectRestResponse<Object> updateScore(Integer score){
        return userService.updateScore(score);
    }

    @ApiOperation(value = "消费积分")
    @PostMapping("/consumeScore")
    public ObjectRestResponse<Object> consumeScore(Integer score){
        return userService.consumeScore(score);
    }




}

