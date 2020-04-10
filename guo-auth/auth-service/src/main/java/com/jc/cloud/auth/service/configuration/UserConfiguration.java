package com.jc.cloud.auth.service.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UserConfiguration
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
@Configuration
@Data
public class UserConfiguration {
    @Value("${auth.jwt.token-header}")
    private String userTokenHeader;
}
