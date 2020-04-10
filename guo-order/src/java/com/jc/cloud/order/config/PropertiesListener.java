package com.jc.cloud.order.config;

import com.jc.cloud.order.alipay.AlipayProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
*   配置文件监听器，用来加载自定义配置文件
 *   @author lgh
 *   @date May 25, 2019
* */
@Component
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        AlipayProperties.loadProperties();
    }
}
