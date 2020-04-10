package com.jc.cloud.auth.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.cloud.auth.service.entity.AuthClient;

import java.util.List;

/**
 * <p>
 * 客户端授权 Mapper 接口
 * </p>
 *
 * @author fangliai
 * @since 2019-04-13
 */
public interface AuthClientMapper extends BaseMapper<AuthClient> {

    List<String> selectAllowedClient(String serverId);

    List<AuthClient> selectAuthorityServiceInfo(String clientId);

}
