package com.jc.message.core.entity.vedio;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseVedioMsg implements Serializable {
    protected String vedioId;
    protected String vedioPath;
    protected int msgType;

    public BaseVedioMsg(){}

    public BaseVedioMsg(String vedioId, String vedioPath, int msgType) {
        this.vedioId = vedioId;
        this.vedioPath = vedioPath;
        this.msgType = msgType;
    }
}
