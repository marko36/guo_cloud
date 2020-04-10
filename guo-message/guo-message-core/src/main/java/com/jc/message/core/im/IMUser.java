package com.jc.message.core.im;

import lombok.Data;

import java.io.Serializable;

@Data
public class IMUser implements Serializable {
    private String userId;
    private String userName;
    private String portrait;

    public IMUser(){}

    public IMUser(String userId){
        this.userId = userId;
    }
}
