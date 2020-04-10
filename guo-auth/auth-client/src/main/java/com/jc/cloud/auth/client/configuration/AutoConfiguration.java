package com.jc.cloud.auth.client.configuration;


import com.jc.cloud.auth.client.config.ServiceAuthConfig;
import com.jc.cloud.auth.client.config.UserAuthConfig;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AutoConfiguration
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@Configuration
@ComponentScan({"com.jc.cloud.auth.client"})
@EnableFeignClients({"com.jc.cloud.auth.client.feign"})
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }

}
