package com.jc.message.Listener;

import com.jc.cloud.common.enums.SystemModelEnum;
import com.jc.message.core.constant.QueryType;
import com.jc.message.core.entity.Message;
import com.jc.message.core.entity.vedio.DispatchVedioMsg;
import com.jc.message.webscoker.WebsocketStarter;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author lgh
 * @Date 2019/6/9
 * @Version 1.0
* */
@Component
public class MonitorListener {
    private static final Logger log = LoggerFactory.getLogger(MonitorListener.class);

    /**
     * 调派事件
     * @param dispatchMsgMessage
     * @param message
     * @param channel
     * */
    @RabbitListener(queues = QueryType.DISPATCH_QUERY)
    public void onDispatchMsg(@Payload Message<DispatchVedioMsg> dispatchMsgMessage, org.springframework.amqp.core.Message message, Channel channel){
        try {
            WebsocketStarter.getInstance().sendGroupMsg(SystemModelEnum.USER.name(),dispatchMsgMessage);
            WebsocketStarter.getInstance().sendGroupMsg(SystemModelEnum.ADMIN.name(),dispatchMsgMessage);
        }catch (Exception e){
            log.error("onDispatchMsg send error:"+e.getMessage());
        }
    }
}
