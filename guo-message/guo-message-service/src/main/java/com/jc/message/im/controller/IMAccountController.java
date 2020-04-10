package com.jc.message.im.controller;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.message.im.service.impl.IMAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/msg/im")
public class IMAccountController {
    private static final Logger log = LoggerFactory.getLogger(IMAccountController.class);

    @Autowired
    IMAccountService imAccountService;

    @RequestMapping("/list")
    public  Object list(){
        try {
            return new ObjectRestResponse<>().data(imAccountService.getAll());
        }catch (Exception e){
            log.error("获取IM服务商失败，error:{}",e.getMessage());
            return new ObjectRestResponse<>().error("error");
        }
    }

    @RequestMapping("/update")
    public Object update(@RequestParam Map<String ,Object> account){
        Object ob = imAccountService.getAccountByType(account.get("imType").toString());
        if (ob != null){
            try{
                //SpringBeanUtil.map2BeanJava(account,ob);
                imAccountService.editAccount(ob);
                return new ObjectRestResponse<>().data("ok");
            }catch (Exception e){
                return new ObjectRestResponse<>().error("参数错误");
            }
        }else{
            return new ObjectRestResponse<>().error("找不到相应的通道");
        }
    }
}
