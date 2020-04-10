package com.jc.cloud.auth.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.auth.service.entity.AuthClientService;
import com.jc.cloud.auth.service.mapper.AuthClientServiceMapper;
import com.jc.cloud.auth.service.service.IAuthClientServiceServerice;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端授权 服务实现类
 * </p>
 *
 * @author fangliai
 * @since 2019-04-13
 */
@Service
public class AuthClientServiceServiceImpl extends ServiceImpl<AuthClientServiceMapper, AuthClientService> implements IAuthClientServiceServerice {

    @Override
    public void deleteByServiceId(int id) {
        baseMapper.deleteByServiceId(id);
    }
}
