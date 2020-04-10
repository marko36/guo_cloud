package com.jc.message.core.entity;

import lombok.Data;

@Data
public class MonitorMsg {
    /**
     * 视频id
     * */
    private String vedioId;

    /**
     *  视频路径
    * */
    private String vedioPath;

    public MonitorMsg(){}

    public MonitorMsg(String vedioId, String vedioPath) {
        this.vedioId = vedioId;
        this.vedioPath = vedioPath;
    }
}
