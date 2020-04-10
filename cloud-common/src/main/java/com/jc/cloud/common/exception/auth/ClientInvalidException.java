package com.jc.cloud.common.exception.auth;


import com.jc.cloud.common.constant.ErrorConstants;
import com.jc.cloud.common.exception.BaseException;

/**
 * @ClassName ClientInvalidException
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, ErrorConstants.EX_CLIENT_INVALID_CODE);
    }
}
