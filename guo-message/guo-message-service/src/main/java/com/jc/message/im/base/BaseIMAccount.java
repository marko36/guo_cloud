package com.jc.message.im.base;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseIMAccount implements Serializable {
    private String appid;

    private String imType;

    private boolean isOpen = false;

    public BaseIMAccount(){}

    public BaseIMAccount(String imType){
        this.imType = imType;
    }


}
