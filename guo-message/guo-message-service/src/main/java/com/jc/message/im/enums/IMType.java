package com.jc.message.im.enums;

import com.jc.message.im.base.BaseIMAccount;
import com.jc.message.im.entity.RongCloudAccount;
import com.jc.message.im.service.IMService;
import com.jc.message.im.service.impl.RongCloudIMService;

public enum IMType {

    RONGCLOUD{
        @Override
        public IMService getIMService(){
            return new RongCloudIMService();
        }
        @Override
        public <T extends BaseIMAccount> T getDefalutAccount(){
            return (T) new RongCloudAccount();
        }
    }

    ;
    abstract public <T extends BaseIMAccount> IMService<T> getIMService();
    abstract public <T extends BaseIMAccount> T getDefalutAccount();

}
