package com.jc.cloud.common.util;

import com.jc.cloud.common.enums.ExamineType;
import com.jc.cloud.common.enums.IBaseEnum;

public class EnumUtils {

    public static <E extends IBaseEnum> E getEnumByType(Class<E> clzz ,Object object)throws Exception{
        for (E e:clzz.getEnumConstants()) {
            if(e.getType().equals(object)){
                return e;
            }
        }
        throw new Exception("not find item");
    }
    public static void main(String args[]){
        try {
           ExamineType type= EnumUtils.getEnumByType(ExamineType.class,1);
           System.out.println(type.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
