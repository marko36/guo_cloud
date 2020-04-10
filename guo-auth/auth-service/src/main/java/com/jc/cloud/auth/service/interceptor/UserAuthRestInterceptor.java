package com.jc.cloud.auth.service.interceptor;

import com.jc.cloud.auth.service.configuration.UserConfiguration;
import com.jc.cloud.auth.service.util.user.JwtTokenUtil;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.util.jwt.IJWTInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserAuthRestInterceptor
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserConfiguration userConfiguration;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String token = request.getHeader(userConfiguration.getUserTokenHeader());
        IJWTInfo infoFromToken = jwtTokenUtil.getInfoFromToken(token);
        BaseContextHandler.setScope(infoFromToken.getScope());
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
