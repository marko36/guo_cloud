package com.jc.cloud.auth.client;
import com.jc.cloud.auth.client.configuration.AutoConfiguration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;
/**
 * 开启授权模块
 * @ClassName EnableCloudAuthClient
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfiguration.class)
@Documented
@Inherited
public @interface EnableCloudAuthClient {
}
