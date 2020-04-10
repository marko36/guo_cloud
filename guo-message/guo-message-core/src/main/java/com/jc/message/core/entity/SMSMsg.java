package com.jc.message.core.entity;

import lombok.Data;

import java.io.Serializable;
/**
 * @Author lgh
 * @Date 2019/6/1
 * @Version 1.0
* */
@Data
public class SMSMsg implements Serializable {
    private String phone;
    private String content;

    public SMSMsg(){}

    public SMSMsg(String phone,String content){
        this.phone = phone;
        this.content = content;
    }
}
