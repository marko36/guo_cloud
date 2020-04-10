package com.jc.message;

import com.jc.message.webscoker.WebsocketStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan("com.jc.message")
public class MessageBootstarp {
    private static final Logger log = LoggerFactory.getLogger(MessageBootstarp.class);

    public static void main(String[] args) {
        SpringApplication.run(MessageBootstarp.class,args);
        try {
            WebsocketStarter.getInstance().start();
        }catch (Exception e){
            log.error("开启websocket服务失败,error:"+e.getMessage());
        }
    }
}
