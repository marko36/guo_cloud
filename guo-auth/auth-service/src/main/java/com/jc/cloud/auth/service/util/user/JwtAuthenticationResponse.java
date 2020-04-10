package com.jc.cloud.auth.service.util.user;

import java.io.Serializable;

/**
 * @ClassName JwtAuthenticationResponse
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
