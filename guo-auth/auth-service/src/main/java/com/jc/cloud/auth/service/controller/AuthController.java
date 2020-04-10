package com.jc.cloud.auth.service.controller;

import com.jc.cloud.auth.service.service.IAuthService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.vo.auth.JwtAuthenticationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Value("${auth.jwt.token-header}")
    private String tokenHeader;

    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "/token")
    public ObjectRestResponse<Object> createAuthenticationToken(JwtAuthenticationRequest authenticationRequest) throws Exception {
        return new ObjectRestResponse<>().data(authService.login(authenticationRequest));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ObjectRestResponse<String> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws Exception {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        return new ObjectRestResponse<>().data(refreshedToken);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public ObjectRestResponse<?> verify(String token) throws Exception {
        authService.validate(token);
        return new ObjectRestResponse<>();
    }
}
