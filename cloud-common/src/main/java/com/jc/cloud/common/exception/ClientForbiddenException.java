package com.jc.cloud.common.exception;

import com.jc.cloud.common.constant.ErrorConstants;

public class ClientForbiddenException extends BaseException {

    public ClientForbiddenException() {
        this("cleint token forbidden");
    }
    public ClientForbiddenException(String msg) {
        super(msg, ErrorConstants.EX_BUSINESS_CODE);
    }
}
