package com.jc.cloud.auth.client.interceptor;
import com.jc.cloud.auth.client.annotation.IgnoreUserToken;
import com.jc.cloud.auth.client.config.UserAuthConfig;
import com.jc.cloud.auth.client.jwt.UserAuthUtil;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.exception.BaseException;
import com.jc.cloud.common.util.jwt.IJWTInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserAuthRestInterceptor
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);

    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader(userAuthConfig.getTokenHeader());
        if (StringUtils.isEmpty(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(userAuthConfig.getTokenHeader())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        if(StringUtils.isEmpty(token)){
            token=request.getParameter(userAuthConfig.getTokenHeader());
        }
        IJWTInfo infoFromToken = userAuthUtil.getInfoFromToken(token);
        BaseContextHandler.setUserId(infoFromToken.getUserId());
        BaseContextHandler.setUserName(infoFromToken.getUserName());
        BaseContextHandler.setScope(infoFromToken.getScope());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }

}
