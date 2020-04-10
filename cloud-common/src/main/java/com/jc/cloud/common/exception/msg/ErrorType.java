package com.jc.cloud.common.exception.msg;

/**
 *  错误代码定义
 */
public  enum ErrorType {
    ;
    private String key;
    private String des;
    ErrorType(String key, String des) {
        this.key = key;
        this.des = des;
    }

    public String getKey() {
        return key;
    }
}
