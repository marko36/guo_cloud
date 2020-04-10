package com.jc.cloud.auth.service.service;

import com.jc.cloud.vo.auth.JwtAuthenticationRequest;

public interface IAuthService {
    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;
    String refresh(String oldToken) throws Exception;
    void validate(String token) throws Exception;
}
