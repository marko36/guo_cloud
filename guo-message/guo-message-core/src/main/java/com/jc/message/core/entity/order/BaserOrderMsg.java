package com.jc.message.core.entity.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaserOrderMsg implements Serializable {
    protected String orderId;
    protected String longitude;
    protected String latitude;
    protected int msgType;

    public BaserOrderMsg(String orderId, String longitude, String latitude, int msgType) {
        this.orderId = orderId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.msgType = msgType;
    }

    public BaserOrderMsg(){}
}
