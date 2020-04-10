package com.jc.cloud.common.redis;

public class UserKey extends BasePrefix{

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey getRegister = new UserKey(60,"Registercode:");
}
