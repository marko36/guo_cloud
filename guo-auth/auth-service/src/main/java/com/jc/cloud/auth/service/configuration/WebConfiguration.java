package com.jc.cloud.auth.service.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jc.cloud.auth.service.interceptor.ServiceAuthRestInterceptor;
import com.jc.cloud.auth.service.interceptor.UserAuthRestInterceptor;
import com.jc.cloud.common.handler.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebConfiguration
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
@Configuration("admimWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced // 添加负载均衡支持
    public RestTemplate restTemplate() {
        return builder.build();
    }

    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getServiceAuthRestInterceptor()).addPathPatterns("/service/**");
        registry.addInterceptor(getUserAuthRestInterceptor()).addPathPatterns("/service/**");
    }

    @Bean
    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

   @Bean
   public HttpMessageConverters fastJsonHttpMessageConverters() {
            FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
            FastJsonConfig fastJsonConfig = new FastJsonConfig();
            fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
            fastJsonConfig.setCharset(Charset.forName("utf-8"));

            List<MediaType> fastMediaTypes = new ArrayList<>();
            fastMediaTypes.add(MediaType.APPLICATION_JSON);
            fastConverter.setSupportedMediaTypes(fastMediaTypes);
            fastConverter.setFastJsonConfig(fastJsonConfig);
            HttpMessageConverter<?> converter = fastConverter;
            return new HttpMessageConverters(converter);
    }
}
