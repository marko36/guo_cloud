package com.jc.cloud.gateway.feign;

import com.jc.cloud.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
@FeignClient(value = "${basic.service.id}")
public interface DynamicRouteFeign {

    /**
     * 获取最后版本
     * @return
     */
    @GetMapping("/dynamic/lastVersion")
    ObjectRestResponse<Long> getLastVersion();

    /**
     * 获取路由信息
     * @return
     */
    @GetMapping("/dynamic/routes")
    ObjectRestResponse<List<Object>>  getRoutes();

}
