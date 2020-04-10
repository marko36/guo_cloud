package com.jc.message.im.service;

import com.jc.message.core.im.IMMessage;
import com.jc.message.core.im.IMUser;
import com.jc.message.im.base.BaseIMAccount;

public interface IMService<T extends BaseIMAccount> {
    public void setAccount(T t);

    /**
     *  注册用户
     * @param user
     * @throws Exception
     * */
    public void register(IMUser user)throws Exception;

    /**
     * 获取用户签名/token
     * @param user
     * @return
     * @throws Exception
     * */
    public String getToken(IMUser user)throws Exception;

    /**
     * 发送消息
     * @param message
     * */
    public void sendMessage(IMMessage message)throws Exception;

}
