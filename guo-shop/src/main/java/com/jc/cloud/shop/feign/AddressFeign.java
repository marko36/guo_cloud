package com.jc.cloud.shop.feign;

import com.jc.cloud.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "${guo.order.service.id}")
public interface AddressFeign {

    /**
     * 获取当前用户默认地址
     * */
    @GetMapping("/v1/address/getDefault")
    ObjectRestResponse<Object> getDefalutAddress();
}
