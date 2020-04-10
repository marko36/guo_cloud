package com.jc.cloud.vo.auth;

import java.io.Serializable;

/**
 * @ClassName JwtAuthenticationRequest
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/

public class JwtAuthenticationResponse implements Serializable {

    /**
     * 用户ID
     * @return
     */
    private String uid;

    /**
     * 学校Id
     * @return
     */
    private String scope;

    private String userName;

    public JwtAuthenticationResponse(){

    }
    public JwtAuthenticationResponse(String uid, String userName,String scope) {
        this.uid = uid;
        this.scope = scope;
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
