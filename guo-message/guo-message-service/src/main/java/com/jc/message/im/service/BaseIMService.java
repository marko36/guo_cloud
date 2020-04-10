package com.jc.message.im.service;

import com.jc.message.im.base.BaseIMAccount;

public abstract class BaseIMService<T extends BaseIMAccount> implements IMService<T>{
    protected T account;

    @Override
    public void setAccount(T t){
        this.account = t;
    }
}
