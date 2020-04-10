package com.jc.cloud.auth.client.interceptor;



import com.jc.cloud.auth.client.annotation.IgnoreClientToken;
import com.jc.cloud.auth.client.config.ServiceAuthConfig;
import com.jc.cloud.auth.client.jwt.ServiceAuthUtil;
import com.jc.cloud.common.exception.ClientForbiddenException;
import com.jc.cloud.common.util.jwt.IJWTInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.support.ResourceHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 服务端签权拦截器
 * @ClassName ServiceAuthRestInterceptor
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@SuppressWarnings("ALL")
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ServiceAuthRestInterceptor.class);
    @Value("${spring.profiles.active:dev}")
    private String active;

    @Autowired
    private ServiceAuthUtil serviceAuthUtil;

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    private List<String> allowedClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if((handler instanceof ResourceHttpRequestHandler  || handler instanceof  HandlerMethod) && active.equals("dev") ){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行服务拦截
        IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }

        String token = request.getHeader(serviceAuthConfig.getTokenHeader());
        try {
            serviceAuthUtil.getInfoFromToken(token);
            return super.preHandle(request, response, handler);
        }catch (Exception e){
            throw new ClientForbiddenException("Client is Forbidden!");
        }

//        IJWTInfo infoFromToken = serviceAuthUtil.getInfoFromToken(token);
//        String uniqueName = infoFromToken.getUserName();
//        for(String client:serviceAuthUtil.getAllowedClient()){
//            if(client.equals(uniqueName)){
//                return super.preHandle(request, response, handler);
//            }
//        }
//        throw new ClientForbiddenException("Client is Forbidden!");
    }
}
