package com.jc.cloud.auth.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.auth.service.entity.AuthClientService;

/**
 * <p>
 * 客户端授权 服务类
 * </p>
 *
 * @author fangliai
 * @since 2019-04-13
 */
public interface IAuthClientServiceServerice extends IService<AuthClientService> {
    void deleteByServiceId(int id);
}
