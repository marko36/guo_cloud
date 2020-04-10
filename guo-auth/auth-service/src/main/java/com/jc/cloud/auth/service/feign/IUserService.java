package com.jc.cloud.auth.service.feign;

import com.jc.cloud.auth.service.configuration.FeignConfiguration;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.vo.auth.JwtAuthenticationRequest;
import com.jc.cloud.vo.auth.JwtAuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @ClassName IUserService
 * @Description //TODO
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
@FeignClient(value = "${basic.service.id}",configuration = FeignConfiguration.class)
public interface IUserService {
  @RequestMapping(value = "/user/login", method = RequestMethod.POST)
  public ObjectRestResponse<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest authenticationRequest);

}
