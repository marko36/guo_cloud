package com.jc.cloud.common.util.jwt;
/**
 * @ClassName IJWTInfo
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public interface IJWTInfo {

    /**
     * 用户域
     * @return
     */
    String getScope();
    /**
     * 用户ID
     * @return
     */
    String getUserId();


    /**
     * 登录名称
     * @return
     */
    String getUserName();

}
