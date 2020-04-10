package com.jc.cloud.common.exception;


import com.jc.cloud.common.constant.ErrorConstants;
import com.jc.cloud.common.exception.msg.ErrorType;

/**
 * 业务错误
 */
public class BusinessException extends BaseException {
    public BusinessException(ErrorType errorType) {
        super(errorType);
    }
    public BusinessException() {
        super("error", ErrorConstants.EX_BUSINESS_CODE);
    }
    public BusinessException(String message) {
        super(message, ErrorConstants.EX_BUSINESS_CODE);
    }
}
