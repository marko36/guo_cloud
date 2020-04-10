package com.jc.cloud.basic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jc.cloud.basic.entity.GatewayRoutes;
import com.jc.cloud.basic.mapper.GatewayRoutesMapper;
import com.jc.cloud.basic.service.IGatewayRoutesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fangliai
 * @since 2019-05-22
 */
@Service
public class GatewayRoutesServiceImpl extends ServiceImpl<GatewayRoutesMapper, GatewayRoutes> implements IGatewayRoutesService {

    @Override
    public List<Map<String, Object>> getRoutes() {
        List<GatewayRoutes> routes=list();
        List<Map<String,Object>> dataMaps=new ArrayList<>();
        routes.forEach((v)->{
            if(!v.getIsDel() && v.getIsEbl()){
                Map<String,Object> map=new HashMap<>();
                map.put("id",v.getRouteId());
//                map.put("routeId",v.getRouteId());
                map.put("order",v.getRouteOrder());
                map.put("uri",v.getRouteUri());
                map.put("filters", JSONObject.parse(v.getFilters()));
                map.put("predicates",JSONObject.parse(v.getPredicates()));
                dataMaps.add(map);
            }
        });
        return dataMaps;
    }
}
