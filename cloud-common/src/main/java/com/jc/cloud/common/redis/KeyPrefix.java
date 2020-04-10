package com.jc.cloud.common.redis;

public interface KeyPrefix {

        int expireSeconds();

        String getPrefix();
}
