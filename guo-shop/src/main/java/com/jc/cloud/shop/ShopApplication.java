package com.jc.cloud.shop;

import com.jc.cloud.auth.client.EnableCloudAuthClient;
import com.jc.cloud.common.exception.msg.ExceptionProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.Resource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = "com.jc.cloud")
@EnableDiscoveryClient
@EnableFeignClients({"com.jc.cloud.shop.feign"})
@EnableCloudAuthClient
@MapperScan("com.jc.cloud.shop.**.mapper")
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class,args);
    }
    @Resource
    private ExceptionProperties exceptionProperties;
}
