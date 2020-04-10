package com.jc.cloud.auth.service.controller;


import com.jc.cloud.auth.service.entity.AuthClientService;
import com.jc.cloud.auth.service.service.IAuthClientService;
import com.jc.cloud.auth.service.service.IAuthClientServiceServerice;
import com.jc.cloud.common.msg.ObjectRestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ServiceController
 * @Description //TODO 
 * @Author fangliai
 * @Date 2019/4/13 0013
 * @Version 1.0
 **/
@SuppressWarnings("ALL")
@RestController
@RequestMapping("service")
public class ServiceController{
    @Autowired
    private IAuthClientService authClientService;

    @Autowired
    private IAuthClientServiceServerice clientServiceServerice;

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse modifyUsers(@PathVariable int id, String clients){
        clientServiceServerice.deleteByServiceId(id);
        if (!StringUtils.isEmpty(clients)) {
            String[] mem = clients.split(",");
            for (String m : mem) {
                AuthClientService clientService = new AuthClientService();
                clientService.setServiceId(m);
                clientService.setClientId(id+"");
                clientServiceServerice.save(clientService);
            }
        }
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<AuthClientService> getUsers(@PathVariable int id){
        return new ObjectRestResponse<AuthClientService>().rel(true).data(authClientService.selectAuthorityServiceInfo(id+""));
    }
}
