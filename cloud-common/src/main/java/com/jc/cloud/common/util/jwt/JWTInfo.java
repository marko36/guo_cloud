package com.jc.cloud.common.util.jwt;

import java.io.Serializable;

/**
 * @ClassName JWTInfo
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class JWTInfo implements Serializable,IJWTInfo {
    private String scope;
    private String userId;
    private String userName;


    public JWTInfo(){}

    public JWTInfo( String userId, String userName,String scope) {
        this.scope = scope;
        this.userId = userId;
        this.userName = userName;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JWTInfo jwtInfo = (JWTInfo) o;
        return userId != null ? userId.equals(jwtInfo.userId) : jwtInfo.userId == null;

    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public String getUserId() {
        return this.userId;
    }



    @Override
    public String getUserName() {
        return this.userName;
    }
}
