package com.jc.message.im.service.impl;

import com.jc.message.core.im.IMMessage;
import com.jc.message.core.im.IMUser;
import com.jc.message.im.entity.RongCloudAccount;
import com.jc.message.im.service.BaseIMService;
import com.jc.message.im.service.IMService;
import io.rong.RongCloud;
import io.rong.messages.BaseMessage;
import io.rong.messages.TxtMessage;
import io.rong.methods.user.User;
import io.rong.models.message.PrivateMessage;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author lgh
 * @Date 2019/6/3
 * @Version 1.0
 * */
public class RongCloudIMService extends BaseIMService<RongCloudAccount> implements IMService<RongCloudAccount> {
    private static final Logger log = LoggerFactory.getLogger(RongCloudIMService.class);
    private RongCloud rongCloud;

    @Override
    public void register(IMUser user) throws Exception {
        this.getToken(user);
    }

    @Override
    public String getToken(IMUser user) throws Exception {
        User rongCloudUser = rongCloud.user;
        UserModel userModel = new UserModel()
                .setId(user.getUserId())
                .setName(user.getUserName())
                .setPortrait(user.getPortrait());
        TokenResult result = rongCloudUser.register(userModel);
        return result.getToken();
    }

    @Override
    public void sendMessage(IMMessage message) throws Exception {
        BaseMessage msg = null;
        switch (message.getMessageType()){
            case TEXT:
                msg = new TxtMessage(message.getContent(),"");
                break;
        }
        switch (message.getMessageMode()){
            case PRIVATE:
                sendPrivateMsg(msg,message.getFo(),message.getTo());
                break;
        }
    }

    /**
     * 发送私聊
     * @param msg
     * @param fo
     * @param to
     * @throws Exception
     * */
    private void sendPrivateMsg(BaseMessage msg,IMUser fo,String... to)throws Exception{
        PrivateMessage privateMessage = new PrivateMessage()
                .setSenderId(fo.getUserId())
                .setTargetId(to)
                .setObjectName(msg.getType())
                .setContent(msg)
                .setPushContent("")
                .setVerifyBlacklist(0)
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0);
        try {
            ResponseResult broadcastResult = rongCloud.message.msgPrivate.send(privateMessage);
        }catch (Exception e){
            log.error("send private msg error:{}",e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
