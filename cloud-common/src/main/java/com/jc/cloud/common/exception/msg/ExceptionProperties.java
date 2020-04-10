package com.jc.cloud.common.exception.msg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties("exception")
@RefreshScope
public class ExceptionProperties {
    private String language;
    private List<ExceptionMessageVo> messages;
    private Map<String,ExceptionMessageVo> source=new HashMap<>();

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<ExceptionMessageVo> getMessages() {
        return messages;
    }

    public void setMessages(List<ExceptionMessageVo> messages) throws Exception {
        this.messages = messages;
        int size=messages.size();
        for(int i=0;i<size;i++){
            ExceptionMessageVo  v=messages.get(i);
            if(source.containsKey(v.getKey())){
                throw new Exception("读取异常配制出误,【"+v.getKey()+"】重复定义");
            }
            source.put(v.getKey(),v);
        }
    }
    public ExceptionMessageVo forKey(String key){
        return  source.get(key);
    }

}
