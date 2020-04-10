package com.jc.message.Listener;

import com.jc.message.core.entity.Message;
import com.jc.message.im.service.impl.IMAccountService;
import com.jc.message.push.PushAccountService;
import com.jc.message.webscoker.WebsocketStarter;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private static final Logger log = LoggerFactory.getLogger(OrderListener.class);

    @Autowired
    IMAccountService imAccountService;

    @Autowired
    PushAccountService pushAccountService;

    /**
     * 新建订单事件
     * @param newOrderMsg
     * @param message
     * @param channel
     * */
    @RabbitListener()
    public void onNewOrderMsg(@Payload Message<NewsMsg> newOrderMsg, Message message, Channel channel){
        try {
            WebsocketStarter.getInstance().sendGroupMsg("",newOrderMsg);
        }catch (Exception e){
            log.error("onNewOrderMsg send error:"+e.getMessage());
        }
    }
}
