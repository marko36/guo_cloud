package com.jc.message.tool;

import com.jc.message.core.constant.MessageType;
import com.jc.message.core.constant.QueryType;
import com.jc.message.core.entity.LocationMsg;
import com.jc.message.core.entity.Message;
import com.jc.message.core.constant.VedioMessageType;
import com.jc.message.core.entity.SMSMsg;
import com.jc.message.core.entity.order.BaserOrderMsg;
import com.jc.message.core.entity.vedio.BaseVedioMsg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jc.message.core.constant.OrderMessageType;

/**
 * @ClassName com.jc.message.tool.MessageUtils
 * @Author lgh
 *@Date 2019/6/1
 *@Version 1.0
* */
@Component
public class MessageUtils {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 支付完成后的回调
     * @param message
    * */
    public void sendOrderPayMsg(BaserOrderMsg message){
        switch (message.getMsgType()){
            case OrderMessageType.SERVER_PAY:
                this.rabbitTemplate.convertAndSend(QueryType.ORDER_PAY,new Message<>(message, MessageType.ORDER_PAY_MSG));
                break;

        }
    }

    /**
     *  发送车辆定位消息
     * @param msg
     * */
    public void sendlocationMsg(LocationMsg msg){
        this.rabbitTemplate.convertAndSend(QueryType.CAR_LOCATION_QUERY,new Message<>(msg, MessageType.CAR_LOCATION_MSG));
    }

    /**
     *  发送短信
     * @param message
     * */
    public void sendSMSMsg(Message<SMSMsg> message){
        this.rabbitTemplate.convertAndSend(this.rabbitTemplate.convertSendAndReceive(QueryType.CODE,message));
    }

    /**
     *  监控视频
     * @param message
     * */
    public void sendMonitorMsg(BaseVedioMsg message){
        switch (message.getMsgType()){
            case VedioMessageType.DISPATCH:
                this.rabbitTemplate.convertAndSend(QueryType.DISPATCH_QUERY,new Message<>(message,MessageType.MONITOR_MSG));
                break;
        }
    }

}
