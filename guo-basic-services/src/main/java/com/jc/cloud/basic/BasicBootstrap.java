package com.jc.cloud.basic;
import com.jc.cloud.common.exception.msg.ExceptionProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.Resource;

/**
 * @ClassName HomeBootstrap
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = "com.jc.cloud")
@EnableDiscoveryClient
@MapperScan("com.jc.cloud.basic.**.mapper")
public class BasicBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(BasicBootstrap.class, args);
    }
}
