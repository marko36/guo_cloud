package com.jc.cloud.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.basic.entity.User;
import com.jc.cloud.basic.mapper.UserMapper;
import com.jc.cloud.basic.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.enums.UserType;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.MD5;
import com.jc.cloud.vo.auth.JwtAuthenticationRequest;
import com.jc.cloud.vo.auth.JwtAuthenticationResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author fangliai
 * @since 2019-05-24
 */
@Service
@Log4j2
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public ObjectRestResponse<JwtAuthenticationResponse> login(JwtAuthenticationRequest request) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("phone",request.getPhone());
        queryWrapper.eq("type",request.getScope().toUpperCase());
        if(UserType.MANAGE.getType().equals(request.getScope())){

        }
        User user=null;
        try {
            user=getOne(queryWrapper);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ObjectRestResponse<>().error("数据库连接失败");
        }
        if(user==null){
            return new ObjectRestResponse<>().error("用户不存在");
        }
        if(!user.getPassword().equals(MD5.MD5Encode(request.getPassword()))){
            return new ObjectRestResponse<>().error("密码错误");
        }
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse(user.getUid(), StringUtils.isEmpty(user.getNickName())?user.getPhone():user.getNickName(),user.getType());
        return  new ObjectRestResponse<>().data(jwtAuthenticationResponse);
    }

    @Override
    public ObjectRestResponse<Object> getInfoById(String userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("uid", userId);
        User user = null;
        try {
            user = getOne(queryWrapper);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ObjectRestResponse<>().error("数据库错误");
        }
        return new ObjectRestResponse<>().data(user);
    }

    @Override
    public ObjectRestResponse<Object> updateScore(Integer score) {
        try {
            String userId = BaseContextHandler.getUserId();
            /*QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("uid",userId);*/
            User user = getById(userId);
            user.setScoreCount(user.getScoreCount() + score);
            user.setScore(user.getScore() + score);
            updateById(user);
            return new ObjectRestResponse<>().ok();
        }catch (Exception e){
            return new ObjectRestResponse<>().error("添加失败");
        }
    }

    @Override
    public ObjectRestResponse<Object> consumeScore(Integer score) {
        try {
            User user = getById(BaseContextHandler.getUserId());
            user.setUseScore(user.getUseScore() + score);
            user.setScore(user.getScore() - score);
            updateById(user);
            return new ObjectRestResponse<>().ok();
        }catch (Exception e){
            return new ObjectRestResponse<>().error("消费失败");
        }
    }
}
