package com.jc.cloud.auth.service.configuration;


import com.jc.cloud.auth.service.interceptor.ClientTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FeignConfiguration
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
@Configuration
public class FeignConfiguration {
    @Bean
    ClientTokenInterceptor getClientTokenInterceptor(){
        return new ClientTokenInterceptor();
    }




}
