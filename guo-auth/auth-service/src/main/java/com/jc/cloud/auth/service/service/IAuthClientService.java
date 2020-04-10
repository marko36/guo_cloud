package com.jc.cloud.auth.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.auth.service.entity.AuthClient;

import java.util.List;

/**
 * <p>
 * 客户端授权 服务类
 * </p>
 *
 * @author fangliai
 * @since 2019-04-13
 */
public interface IAuthClientService extends IService<AuthClient> {
    public String apply(String clientId, String secret) throws Exception;

    /**
     * 获取授权的客户端列表
     * @param serviceId
     * @param secret
     * @return
     */
    public List<String> getAllowedClient(String serviceId, String secret);

    /**
     * 获取服务授权的客户端列表
     * @param serviceId
     * @return
     */
    public List<String> getAllowedClient(String serviceId);

    public void registryClient();

    public void validate(String clientId, String secret) throws Exception;

    public  List<AuthClient> selectAuthorityServiceInfo(String clientId);
}
