package com.jc.cloud.gateway.filter;

import com.jc.cloud.auth.client.config.ServiceAuthConfig;
import com.jc.cloud.auth.client.config.UserAuthConfig;
import com.jc.cloud.auth.client.jwt.ServiceAuthUtil;
import com.jc.cloud.auth.client.jwt.UserAuthUtil;
import com.jc.cloud.common.context.BaseContextHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

/**
 * 可对客户端header 中的 Authorization 信息进行认证
 */
@Configuration
@Log4j2
public class TokenAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private ServiceAuthUtil serviceAuthUtil;


    @Value("${gateway.ignore.startWith}")
    private String startWith;
    private static final String GATE_WAY_PREFIX = "/api";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("check token and user permission....");
        LinkedHashSet requiredAttribute = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = exchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();
        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()){
                URI next = iterator.next();
//                if(next.getPath().startsWith(GATE_WAY_PREFIX)){
//                    requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
//                }
            }
        }
        final String method = request.getMethod().toString();
        BaseContextHandler.setToken(null);
        ServerHttpRequest.Builder mutate = request.mutate();
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            ServerHttpRequest build = mutate.build();
            return chain.filter(exchange.mutate().request(build).build());
        }

        List<String> strings = request.getHeaders().get(userAuthConfig.getTokenHeader());
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0);
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(authToken)) {
            strings = request.getQueryParams().get(userAuthConfig.getTokenHeader());
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        if(!StringUtils.isEmpty(authToken)){
            mutate.header(userAuthConfig.getTokenHeader(), authToken);
            BaseContextHandler.setToken(authToken);
        }

//        IJWTInfo user = null;
//        try {
//            user = getJWTUser(request, mutate);
//        } catch (Exception e) {
//            log.error("用户Token过期异常", e);
//            return getVoidMono(serverWebExchange, new TokenForbiddenResponse("User Token Forbidden or Expired!"));
//        }
//        List<PermissionInfo> permissionIfs = userService.getAllPermissionInfo();
//        // 判断资源是否启用权限约束
//        Stream<PermissionInfo> stream = getPermissionIfs(requestUri, method, permissionIfs);
//        List<PermissionInfo> result = stream.collect(Collectors.toList());
//        PermissionInfo[] permissions = result.toArray(new PermissionInfo[]{});
//        if (permissions.length > 0) {
//            if (checkUserPermission(permissions, serverWebExchange, user)) {
//                return getVoidMono(serverWebExchange, new TokenForbiddenResponse("User Forbidden!Does not has Permission!"));
//            }
//        }
        // 申请客户端密钥头
        mutate.header(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken());
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }


    /**
     * 自定义返回错误信息
     * @param response
     * @param status
     * @param message
     * @return
     */
    public DataBuffer responseErrorInfo(ServerHttpResponse response , String status ,String message){
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        Map<String,String> map = new HashMap<>();
        map.put("status",status);
        map.put("message",message);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(map.toString().getBytes());
        return bodyDataBuffer;
    }


    @Override
    public int getOrder() {
        return 2;
    }
}