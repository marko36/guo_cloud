package com.cloud.id.client.feign;

import com.jc.cloud.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName ServiceAuthFeign
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/14 0014
 * @Version 1.0
 **/
@FeignClient(value = "cloud-idwroker",configuration = {})
public interface IdWorkerFeign {

    @RequestMapping(value = "/idworker/list")
    public ObjectRestResponse<String> list(@RequestParam("ipu") String ipu);


    @RequestMapping(value = "/idworker/see",method = RequestMethod.GET)
    public ObjectRestResponse<String> see();

    @RequestMapping(value = "/idworker/inc",method = RequestMethod.GET)
    public ObjectRestResponse<String> inc(@RequestParam("ipu") String ipu);


    @RequestMapping(value = "/idworker/sync",method = RequestMethod.GET)
    public ObjectRestResponse<String> sync(@RequestParam("ipu") String ipu, @RequestParam("ids") String ids);

}
