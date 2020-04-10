package com.jc.cloud.gateway;
import com.jc.cloud.auth.client.EnableCloudAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName GatewayServerBootstrap
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = "com.jc.cloud")
@EnableDiscoveryClient
@EnableCloudAuthClient
@EnableFeignClients({"com.jc.cloud.gateway.feign"})
public class GatewayServerBootstrap {
    public static void main(String[] args) {
        //        DBLog.getInstance().start();
        SpringApplication.run(GatewayServerBootstrap.class, args);
    }
}
