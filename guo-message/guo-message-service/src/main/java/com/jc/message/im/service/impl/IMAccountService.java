package com.jc.message.im.service.impl;

import com.jc.cloud.common.redis.IMKey;
import com.jc.cloud.common.util.RedisUtil;
import com.jc.message.im.base.BaseIMAccount;
import com.jc.message.im.enums.IMType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class IMAccountService {
    private static final Logger log = LoggerFactory.getLogger(IMAccountService.class);

    private static Random random = new Random();
    @Autowired
    RedisUtil redisUtil;

    private static final String IMACCOUNT_KEY = "im_account";

    public void init(){
        for (IMType type:IMType.values()){
            if (redisUtil.get(IMACCOUNT_KEY+type.name()) == null){
                redisUtil.set(IMKey.IMAccount,IMACCOUNT_KEY,type.getDefalutAccount());
            }
        }
    }

    public <T extends BaseIMAccount>List<T> getAll(){
        List<T> accounts = new ArrayList<>();
        redisUtil.hmget(IMKey.IMAccount+IMACCOUNT_KEY).forEach((k,v)->{
            accounts.add((T) v);
        });
        return accounts;
    }

    public <T extends BaseIMAccount> T getAccountByType(IMType type){
        return getAccountByType(type.name());
    }
    public <T extends BaseIMAccount> T getAccountByType(String type){
        return (T)redisUtil.get(IMKey.IMAccount+type);
    }

    public BaseIMAccount getAccount(){
        List<BaseIMAccount> accounts = getAll();
        if (accounts == null || accounts.isEmpty()){
            return null;
        }
        if (accounts.size() == 1 && accounts.get(0).isOpen()){
            return accounts.get(0);
        }
        List<BaseIMAccount> opens = new ArrayList<>();
        getAll().forEach((k)->{
            if (k.isOpen()){
                opens.add(k);
            }
        });
        if (opens.isEmpty()){
            return null;
        }
        return opens.get(random.nextInt(opens.size()-1));
    }


    public void editAccount(Object ob) {
        redisUtil.set(IMKey.IMAccount,IMACCOUNT_KEY,ob);
    }
}
