package com.jc.cloud.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.basic.entity.CmsUser;
import com.jc.cloud.basic.entity.User;
import com.jc.cloud.basic.mapper.CmsUserMapper;
import com.jc.cloud.basic.service.ICmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * 后台管理员 服务实现类
 * </p>
 *
 * @author fangliai
 * @since 2019-06-11
 */
@Service
@Log4j2
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements ICmsUserService {

    @Override
    public ObjectRestResponse<JwtAuthenticationResponse> login(JwtAuthenticationRequest request) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",request.getPhone());

        CmsUser user=null;
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
        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse(user.getUid(), user.getUsername(),UserType.MANAGE.getType());
        return  new ObjectRestResponse<>().data(jwtAuthenticationResponse);
    }
}
