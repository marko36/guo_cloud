package com.jc.cloud.common.exception.msg;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExceptionLocalDataSource {
    @Autowired
    private ExceptionProperties exceptionProperties;

    public ExceptionMessageVo getError(String key){
        return exceptionProperties.forKey(key);
    }

    public String msg(ExceptionMessageVo message){
        if(exceptionProperties.getLanguage().equals("zh")){
            return StringUtils.isEmpty(message.getZh())?message.getEn():message.getZh();
        }else {
            return StringUtils.isEmpty(message.getEn())?message.getZh():message.getEn();
        }
    }
}
