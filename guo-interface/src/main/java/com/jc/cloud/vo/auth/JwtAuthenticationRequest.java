package com.jc.cloud.vo.auth;

import java.io.Serializable;

/**
 * @ClassName JwtAuthenticationRequest
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/

public class JwtAuthenticationRequest implements Serializable {

    /**
     * 用户ID
     * @return
     */
    private String phone;

    /**
     * 学校Id
     * @return
     */
    private String password;

    private String scope;

    public JwtAuthenticationRequest() {
    }

    public JwtAuthenticationRequest(String phone, String password, String scope) {
        this.phone = phone;
        this.password = password;
        this.scope = scope;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
