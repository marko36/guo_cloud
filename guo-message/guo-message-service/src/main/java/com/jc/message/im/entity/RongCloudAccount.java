package com.jc.message.im.entity;

import com.jc.message.im.base.BaseIMAccount;
import com.jc.message.im.enums.IMType;
import lombok.Data;

@Data
public class RongCloudAccount extends BaseIMAccount {
    private String appKey;
    private String appSecret;

    public RongCloudAccount(){
        super(IMType.RONGCLOUD.name());
    }
}
