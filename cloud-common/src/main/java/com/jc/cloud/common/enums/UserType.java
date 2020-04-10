package com.jc.cloud.common.enums;

/***
 * 用户类型
 */
public enum UserType implements IBaseEnum<String> {
    PARENT("parent","家长"),
    SCHOOL("school","学校"),
    MANAGE("manage","平台"),
    MERCHANT("merchant","商户");

    private String type;
    private String des;

    UserType(String type, String des) {
        this.type = type;
        this.des = des;
    }

    @Override
    public String getType() {
        return type;
    }
}
