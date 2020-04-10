package com.jc.cloud.basic.api;


import com.jc.cloud.auth.client.annotation.IgnoreUserToken;
import com.jc.cloud.basic.enums.DynamicTpey;
import com.jc.cloud.basic.service.IDynamicVersionService;
import com.jc.cloud.basic.service.IGatewayRoutesService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fangliai
 * @since 2019-05-22
 */
@RestController
@RequestMapping("/dynamic")
@Log4j2
@IgnoreUserToken
public class GatewayRoutesController {
    @Autowired private IDynamicVersionService dynamicVersionService;
    @Autowired private IGatewayRoutesService gatewayRoutesService;

    @GetMapping("/routes")
    public ObjectRestResponse<List<Object>> getRoutes(){
        try {
            return new ObjectRestResponse<>().ok(gatewayRoutesService.getRoutes());
        }catch (Exception e){
            log.error(e.getMessage());
            return new ObjectRestResponse<>().error("service error");
        }

    }
    @GetMapping("/lastVersion")
    public ObjectRestResponse<Long> getVersion(){
        try {
            return new ObjectRestResponse<Long>().ok(dynamicVersionService.getLastVersion(DynamicTpey.ROUTES));
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }
}

