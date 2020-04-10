package com.jc.cloud.common.util;

/**
 * @ClassName StringHelper
 * @Description // 字符串工具类
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class StringHelper {
    /**
     * object 转 String 类型
     * @param obj
     * @return
     */
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
}
