package com.jc.cloud.auth.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.auth.service.bean.ClientInfo;
import com.jc.cloud.auth.service.entity.AuthClient;
import com.jc.cloud.auth.service.mapper.AuthClientMapper;
import com.jc.cloud.auth.service.service.IAuthClientService;
import com.jc.cloud.auth.service.util.client.ClientTokenUtil;
import com.jc.cloud.common.exception.auth.ClientInvalidException;
import com.jc.cloud.common.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 客户端授权 服务实现类
 * </p>
 *
 * @author fangliai
 * @since 2019-04-13
 */
@Service
    public class AuthClientServiceImpl extends ServiceImpl<AuthClientMapper, AuthClient> implements IAuthClientService {
    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private DiscoveryClient discovery;

    @Override
    public String apply(String clientId, String secret) throws Exception {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("code",clientId);
        AuthClient client=this.getOne(queryWrapper);
        return clientTokenUtil.generateToken(new ClientInfo(client.getCode(),client.getName(),client.getId().toString()));
    }


    @Override
    public List<String> getAllowedClient(String serviceId, String secret) {
        AuthClient info = this.getClient(serviceId, secret);
        List<String> clients = baseMapper.selectAllowedClient(info.getId() + "");
        if(clients==null) {
            new ArrayList<String>();
        }
        return clients;
    }

    @Override
    public List<String> getAllowedClient(String serviceId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("code",serviceId);
        AuthClient client=this.getOne(queryWrapper);
        List<String> clients = baseMapper.selectAllowedClient(client.getId() + "");
        if(clients==null) {
            new ArrayList<String>();
        }
        return clients;
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public void registryClient() {
        // 自动注册节点
        discovery.getServices().forEach((name) ->{
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("code",name);
            wrapper.eq("name",name);
            AuthClient client = getOne(wrapper);
            if(client==null) {
                client.setSecret(UUIDUtils.generateShortUuid());
                save(client);
            }
        });
    }

    @Override
    public void validate(String clientId, String secret) throws Exception {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("code",clientId);
        AuthClient client=this.getOne(queryWrapper);
        if(client==null||!client.getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error!");
        }
    }

    @Override
    public List<AuthClient> selectAuthorityServiceInfo(String clientId) {
        return baseMapper.selectAuthorityServiceInfo(clientId);
    }

    private AuthClient getClient(String clientId, String secret) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("code",clientId);
        AuthClient client=this.getOne(queryWrapper);
        if(client==null||!client.getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error!");
        }
        return client;
    }
}
