package com.jc.cloud.common.exception.msg;

import lombok.Data;

@Data
public class ExceptionMessageVo {
    private int code;
    private String key;
    private String zh;
    private String en;

    public ExceptionMessageVo() {
    }
    public String msg(String language){
        return language.equals("zh")?this.zh:this.en;
    }
}
