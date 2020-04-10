package com.jc.cloud.basic.service.impl;

import com.jc.cloud.basic.entity.AuthClient;
import com.jc.cloud.basic.mapper.AuthClientMapper;
import com.jc.cloud.basic.service.IAuthClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端授权 服务实现类
 * </p>
 *
 * @author fangliai
 * @since 2019-05-22
 */
@Service
public class AuthClientServiceImpl extends ServiceImpl<AuthClientMapper, AuthClient> implements IAuthClientService {

}
