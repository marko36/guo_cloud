package com.jc.cloud.gateway.scheduled;

import com.alibaba.fastjson.JSON;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.gateway.entity.GatewayFilterDefinition;
import com.jc.cloud.gateway.entity.GatewayPredicateDefinition;
import com.jc.cloud.gateway.entity.GatewayRouteDefinition;
import com.jc.cloud.gateway.feign.DynamicRouteFeign;
import com.jc.cloud.gateway.service.DynamicRouteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 定时任务，拉取路由信息
 * 路由信息由路由项目单独维护
 */
@SuppressWarnings("ALL")
@Component
@Log4j2
public class DynamicRouteScheduling {

    @Autowired
    private DynamicRouteFeign dynamicRouteFeign;
    @Autowired private DynamicRouteService dynamicRouteService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String dynamicRouteServerName = "dynamic-route-service";

    //发布路由信息的版本号
    private static Long versionId = 0L;

    //每60秒中执行一次
    //如果版本号不相等则获取最新路由信息并更新网关路由
    @Scheduled(cron = "*/60 * * * * ?")
    public void getDynamicRouteInfo(){
        try{
            log.info("拉取时间:{}" ,dateFormat.format(new Date()));
            //先拉取版本信息，如果版本号不想等则更新路由
            ObjectRestResponse<Long> res=dynamicRouteFeign.getLastVersion();
            if(!res.isSuccess()){
                throw new Exception("获取路由版本失败:"+res.getMessage());
            }
            Long resultVersionId = res.getData();
            log.info("路由版本信息：本地版本号：{}，远程版本号：{}",versionId,resultVersionId);
            if(resultVersionId != null && versionId != resultVersionId){
                log.info("开始拉取路由信息......");
                ObjectRestResponse<List<Object>> routeRes=dynamicRouteFeign.getRoutes();
                if(!routeRes.isSuccess()){
                    throw new Exception("拉取路由信息失败:"+routeRes.getMessage());
                }
                log.info("路由信息:{}",routeRes.getData());
                if(routeRes.getData()!=null && !routeRes.getData().isEmpty()){
                    List<GatewayRouteDefinition> list = JSON.parseArray(JSON.toJSONString(routeRes.getData()) , GatewayRouteDefinition.class);
                    for(GatewayRouteDefinition definition : list){
                        //更新路由
                        RouteDefinition routeDefinition = assembleRouteDefinition(definition);
                        dynamicRouteService.update(routeDefinition);
                    }
                    versionId = resultVersionId;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //把前端传递的参数转换成路由对象
    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = new RouteDefinition();
        definition.setId(gwdefinition.getId());
        definition.setOrder(gwdefinition.getOrder());

        //设置断言
        List<PredicateDefinition> pdList=new ArrayList<>();
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList=gwdefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition: gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);

        //设置过滤器
        List<FilterDefinition> filters = new ArrayList();
        List<GatewayFilterDefinition> gatewayFilters = gwdefinition.getFilters();
        for(GatewayFilterDefinition filterDefinition : gatewayFilters){
            FilterDefinition filter = new FilterDefinition();
            filter.setName(filterDefinition.getName());
            filter.setArgs(filterDefinition.getArgs());
            filters.add(filter);
        }
        definition.setFilters(filters);

        URI uri = null;
        if(gwdefinition.getUri().startsWith("http")){
            uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
        }else{
            uri = URI.create(gwdefinition.getUri());
        }
        definition.setUri(uri);
        return definition;
    }
}