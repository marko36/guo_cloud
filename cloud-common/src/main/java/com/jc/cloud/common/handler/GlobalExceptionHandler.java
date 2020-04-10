package com.jc.cloud.common.handler;

import com.jc.cloud.common.exception.BaseException;
import com.jc.cloud.common.exception.BusinessException;
import com.jc.cloud.common.exception.auth.ClientInvalidException;
import com.jc.cloud.common.exception.auth.ClientTokenException;
import com.jc.cloud.common.exception.auth.UserInvalidException;
import com.jc.cloud.common.exception.auth.UserTokenException;
import com.jc.cloud.common.exception.msg.ExceptionLocalDataSource;
import com.jc.cloud.common.exception.msg.ExceptionMessageVo;
import com.jc.cloud.common.exception.msg.ExceptionProperties;
import com.jc.cloud.common.msg.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionHandler
 * @Description //全局异常处理器
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
@ControllerAdvice("com.jc.cloud")
@ResponseBody
@Log4j2
public class GlobalExceptionHandler {
    @Autowired
    private ExceptionLocalDataSource dataSource;

    @ExceptionHandler(ClientTokenException.class)
    public BaseResponse clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        return new BaseResponse(assembleCode(ex), assembleMsg(ex));
    }

    @ExceptionHandler(ClientInvalidException.class)
    public BaseResponse clientInvalidExceptionHandler(HttpServletResponse response, ClientInvalidException ex) {
        response.setStatus(403);
        return new BaseResponse(assembleCode(ex), assembleMsg(ex));
    }

    @ExceptionHandler(UserTokenException.class)
    public BaseResponse userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        return new BaseResponse(assembleCode(ex), assembleMsg(ex));
    }

    @ExceptionHandler(UserInvalidException.class)
    public BaseResponse userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        return new BaseResponse(assembleCode(ex), assembleMsg(ex));
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        response.setStatus(200);
        return new BaseResponse(assembleCode(ex), assembleMsg(ex));
    }

    private String assembleMsg(BaseException ex){
        if(ex.getErrorType()!=null){
            ExceptionMessageVo message=dataSource.getError(ex.getErrorType().getKey());
            if(message==null){
                return "服务器异常";
            }
           return dataSource.msg(message);
        }
        return ex.getMessage();
    }
    private Integer assembleCode(BaseException ex){
        if(ex.getErrorType()!=null){
            ExceptionMessageVo message=dataSource.getError(ex.getErrorType().getKey());
            if(message==null){
                return 5000;
            }
            return  message.getCode();
        }
        return ex.getStatus();
    }

}
