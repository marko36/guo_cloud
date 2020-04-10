package com.jc.message.push;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static cn.jpush.api.push.model.notification.PlatformNotification.ALERT;

public class PushTools {
    private static final Logger log = LoggerFactory.getLogger(PushTools.class);
    private PushAccountService.JiguangPushAccount pushAccount;

    public PushTools(PushAccountService.JiguangPushAccount pushAccount){
        this.pushAccount = pushAccount;
    }

    public void pushByAlias(String content,String ... alias)throws Exception{
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias())
                .setNotification(Notification.alert(ALERT))
                .setMessage(Message.content(content))
                .build();
        doPush(pushPayload);
    }

    public void pushByTag(String content,String ...tags)throws Exception{
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(tags))
                .setNotification(Notification.alert(ALERT))
                .setMessage(Message.content(content))
                .build();
        doPush(pushPayload);
    }

    private void doPush(PushPayload pushPayload) throws Exception{
        JPushClient client = null;
        try {
            client = getClient();
            PushResult result = getClient().sendPush(pushPayload);
            log.info("Got result -"+ result);
        }catch (APIConnectionException e){
            log.error("Connection error,should retry later",e);
        }catch (APIRequestException e){
            log.error("Should review the error, and fix the request",e);
            log.info("HTTP Status:" + e.getStatus());
            log.info("Error Code:" + e.getErrorCode());
            log.info("Error Message:" + e.getErrorMessage());
        }finally {
            if (client != null){
                client.close();
            }
        }
    }
    public JPushClient getClient() throws Exception{
            return new JPushClient(pushAccount.getAppSecret(),pushAccount.getAppKey(),null, ClientConfig.getInstance());
    }
}
