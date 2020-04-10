package com.jc.message.push;

import com.jc.cloud.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class PushAccountService {
    private static final String JIGUANG_PUSH_CACHE_KEY = "jiguang_push_config";

    @Autowired
    RedisUtil redisUtil;

    public JiguangPushAccount getPushAccount(){
        Object ob = redisUtil.get(JIGUANG_PUSH_CACHE_KEY);
        if (ob == null){
            return new JiguangPushAccount();
        }
        return (JiguangPushAccount) ob;
    }

    public void edit(JiguangPushAccount account){
        redisUtil.set(JIGUANG_PUSH_CACHE_KEY,account);
    }

    public static class JiguangPushAccount implements Serializable{

        private String appKey;
        private String appSecret;

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }
    }
}
