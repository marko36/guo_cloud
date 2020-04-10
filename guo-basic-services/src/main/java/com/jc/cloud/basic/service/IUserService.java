package com.jc.cloud.basic.service;

import com.jc.cloud.basic.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.vo.auth.JwtAuthenticationRequest;
import com.jc.cloud.vo.auth.JwtAuthenticationResponse;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author fangliai
 * @since 2019-05-24
 */
public interface IUserService extends IService<User> {

    ObjectRestResponse<JwtAuthenticationResponse> login(JwtAuthenticationRequest request);

    ObjectRestResponse<Object> getInfoById(String userId);

    ObjectRestResponse<Object> updateScore(Integer score);

    ObjectRestResponse<Object> consumeScore(Integer score);
}
