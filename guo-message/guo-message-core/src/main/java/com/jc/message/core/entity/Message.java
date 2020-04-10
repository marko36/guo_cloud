package com.jc.message.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class Message<T> implements Serializable {
    private String messageId = UUID.randomUUID().toString().replaceAll("-","");
    private T content;
    private Integer msgType;
    private Long sendTime;

    public Message(){}

    public Message(T content,Integer msgType){
        super();
        this.content = content;
        this.msgType = msgType;
        this.sendTime = new Date().getTime();
    }
}
