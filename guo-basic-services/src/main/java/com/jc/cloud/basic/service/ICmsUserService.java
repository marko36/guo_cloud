package com.jc.cloud.basic.service;

import com.jc.cloud.basic.entity.CmsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.vo.auth.JwtAuthenticationRequest;
import com.jc.cloud.vo.auth.JwtAuthenticationResponse;

/**
 * <p>
 * 后台管理员 服务类
 * </p>
 *
 * @author fangliai
 * @since 2019-06-11
 */
public interface ICmsUserService extends IService<CmsUser> {
    ObjectRestResponse<JwtAuthenticationResponse> login(JwtAuthenticationRequest request);
}
