package com.jc.cloud.auth.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName IgnoreUserToken
 * @Description // 忽略用户鉴权
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface IgnoreUserToken {
}
