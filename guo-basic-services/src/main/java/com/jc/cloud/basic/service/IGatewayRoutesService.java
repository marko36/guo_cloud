package com.jc.cloud.basic.service;

import com.jc.cloud.basic.entity.GatewayRoutes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fangliai
 * @since 2019-05-22
 */
public interface IGatewayRoutesService extends IService<GatewayRoutes> {

    List<Map<String,Object>>  getRoutes();
}
