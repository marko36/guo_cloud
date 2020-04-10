package com.cloud.id.client.configuration;

import com.cloud.id.client.SidUtils;
import com.cloud.id.client.feign.IdWorkerFeign;
import com.cloud.id.client.strategy.DefaultWorkerIdStrategy;
import org.springframework.beans.factory.annotation.Autowired;
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
@ComponentScan({"com.cloud.id.client"})
@EnableFeignClients({"com.cloud.id.client.feign"})
public class AutoConfiguration {
    @Autowired
    private IdWorkerFeign idWorkerFeign;
    @Bean
    SidUtils getSid(){
        DefaultWorkerIdStrategy defaultWorkerIdStrategy=new DefaultWorkerIdStrategy(idWorkerFeign);
        return new SidUtils(defaultWorkerIdStrategy);
    }
}
