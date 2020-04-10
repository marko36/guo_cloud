package com.jc.cloud.common.enums;

public enum ExamineType implements IBaseEnum<Integer> {
    DSC(1,"待审核");

    private Integer type;

    private String des;

    ExamineType(int type, String des) {
        this.type = type;
        this.des = des;
    }

    @Override
    public Integer getType() {
        return type;
    }

}
