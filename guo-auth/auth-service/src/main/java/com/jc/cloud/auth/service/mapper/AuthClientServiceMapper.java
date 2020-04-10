package com.jc.cloud.auth.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.cloud.auth.service.entity.AuthClientService;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangliai
 * @since 2019-04-13
 */
public interface AuthClientServiceMapper extends BaseMapper<AuthClientService> {
    void deleteByServiceId(int id);
}
