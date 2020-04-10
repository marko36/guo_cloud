package com.jc.cloud.common.exception;

import com.jc.cloud.common.exception.msg.ErrorType;
import com.jc.cloud.common.exception.msg.ExceptionMessageVo;

/**
 * @ClassName BaseException
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class BaseException extends RuntimeException {
    private int status = 200;
    private ErrorType errorType;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseException(ErrorType errorType) {
        this.errorType=errorType;
    }


    public BaseException(String message,int status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
