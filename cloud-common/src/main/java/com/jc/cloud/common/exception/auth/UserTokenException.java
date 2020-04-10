package com.jc.cloud.common.exception.auth;


import com.jc.cloud.common.constant.ErrorConstants;
import com.jc.cloud.common.exception.BaseException;
import com.jc.cloud.common.exception.msg.ErrorType;

/**
 * @ClassName UserTokenException
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, ErrorConstants.EX_USER_PASS_INVALID_CODE);
    }
}
