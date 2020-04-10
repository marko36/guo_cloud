package com.jc.cloud.auth.service.service.impl;


import com.jc.cloud.auth.service.feign.IUserService;
import com.jc.cloud.auth.service.service.IAuthService;
import com.jc.cloud.auth.service.util.user.JwtTokenUtil;
import com.jc.cloud.common.exception.auth.UserInvalidException;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.jwt.JWTInfo;
import com.jc.cloud.vo.auth.JwtAuthenticationRequest;
import com.jc.cloud.vo.auth.JwtAuthenticationResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IUserService userService;


    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        ObjectRestResponse<JwtAuthenticationResponse> infoRes = userService.login(authenticationRequest);
        if(!infoRes.isSuccess()){
            log.error(infoRes.getMessage());
            throw new UserInvalidException(infoRes.getMessage());
        }
        return  jwtTokenUtil.generateToken(new JWTInfo(infoRes.getData().getUid(),infoRes.getData().getUserName(),infoRes.getData().getScope()));
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }
}
