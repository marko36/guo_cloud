package com.jc.cloud.common.context;

import com.jc.cloud.common.constant.ContextConstants;
import com.jc.cloud.common.util.StringHelper;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName BaseContextHandler
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class BaseContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    /**
     * 获取token信息
     * @return
     */
    public static String getToken(){
        return  StringHelper.getObjectValue(get(ContextConstants.TOKEN));
    }

    public static String getUserId(){return StringHelper.getObjectValue(get(ContextConstants.JWT_KEY_USER_ID));}

    public static String getBabyId(){return StringHelper.getObjectValue(get(ContextConstants.JWT_KEY_BABY_ID));}

    public static String getParentId(){return  StringHelper.getObjectValue(get(ContextConstants.JWT_KEY_PARENT_ID));}

    public static String getSchoolId(){return StringHelper.getObjectValue(get(ContextConstants.JWT_KEY_SCHOOL_ID));}

    public static String getUserName(){return StringHelper.getObjectValue(get(ContextConstants.JWT_KEY_USER_NAME));}

    public static String getScope(){return StringHelper.getObjectValue(get(ContextConstants.JWT_KEY_SCOPE));}

    public static void setToken(String token){
        set(ContextConstants.TOKEN,token);
    }

    public static void setUserId(String userId){
        set(ContextConstants.JWT_KEY_USER_ID,userId);
    }

    public static void setSchoolId(String schoolId){
        set(ContextConstants.JWT_KEY_SCHOOL_ID,schoolId);
    }
    public static void setUserName(String userName){
        set(ContextConstants.JWT_KEY_USER_NAME,userName);
    }

    public static void setBabyId(String babyId){
        set(ContextConstants.JWT_KEY_BABY_ID,babyId);
    }
    public static void setParentId(String parentId){
        set(ContextConstants.JWT_KEY_PARENT_ID,parentId);
    }

    public static void setScope(String scope){
        set(ContextConstants.JWT_KEY_SCOPE,scope);
    }


    private static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    private static Object get(String key){
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static void remove(){
        threadLocal.remove();
    }

}
