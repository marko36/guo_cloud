package com.jc.cloud.common.exception.auth;


import com.jc.cloud.common.constant.ErrorConstants;
import com.jc.cloud.common.exception.BaseException;

/**
 * @ClassName UserInvalidException
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, ErrorConstants.EX_USER_INVALID_CODE);
    }
}
