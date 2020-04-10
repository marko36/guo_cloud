package com.jc.message.push;

import cn.jpush.api.push.PushResult;
import com.jc.cloud.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/push")
public class PushAccountController {

    @Autowired
    PushAccountService accountService;

    @GetMapping("/account")
    public Object getPushAccount(){
        return new ObjectRestResponse<>().data(accountService.getPushAccount());
    }

    @PostMapping("/edit")
    public Object edit(PushAccountService.JiguangPushAccount account){
        accountService.edit(account);
        return new ObjectRestResponse<>().ok();
    }
}
