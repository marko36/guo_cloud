package com.jc.message.core.im;

import lombok.Data;

@Data
public class IMMessage {
    private MessageModeTyep messageMode;
    private MessageType messageType;
    private String content;
    private IMUser fo;
    private String[] to;

    public IMMessage(){}

    public IMMessage(String content,IMUser fo,String... to){
        this.content = content;
        this.fo = fo;
        this.to = to;
    }
}
