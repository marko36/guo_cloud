package com.jc.cloud.common.util;

import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {

    /**
     * 日期操作
     * @param field  Calendar常量定义
     * @param amount
     * @return
     */
    public static Date compute(int field,int amount){
        return compute(new Date(),field,amount);
    }

    /**
     * 日期计算
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date compute(Date date, int field,int amount){
        GregorianCalendar gc=new GregorianCalendar();
        gc.setTime(date);
        gc.add(field,amount);
        return gc.getTime();
    }

}
