package com.jc.message.tool;

import com.jc.message.core.constant.QueryType;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
     * @Author lgh
     * @Data 2019/6/3
     * @Version 1.0
     * */
@Configuration
public class QueryConfig {

    @Bean
    public Queue smsQueueMessage(){
        return new Queue(QueryType.CODE);
    }

    @Bean
    public Queue carLocationQueueMessage(){
        return new Queue(QueryType.CAR_LOCATION_QUERY,true);
    }

    @Bean
    public Queue monitorQueueMessage(){
        return new Queue(QueryType.MONITOR_QUERY,true);
    }

    @Bean
    public FanoutExchange smsFanoutExchange(){
        return new FanoutExchange(QueryType.CODE + "—exchange");
    }

    @Bean
    public FanoutExchange carLocationFanoutExchange(){
        return new FanoutExchange(QueryType.CAR_LOCATION_QUERY + "—exchange");
    }

    @Bean
    public FanoutExchange monitorFanoutExchange(){
        return new FanoutExchange(QueryType.MONITOR_QUERY + "—exchange");
    }

    @Bean
    public Binding smsFanoutBinding(){
        return BindingBuilder.bind(smsQueueMessage()).to(smsFanoutExchange());
    }

    @Bean
    public Binding carLocationFanoutBinding(){
        return BindingBuilder.bind(carLocationQueueMessage()).to(carLocationFanoutExchange());
    }

    @Bean
    public Binding monitorFanoutBinding(){
        return BindingBuilder.bind(monitorQueueMessage()).to(monitorFanoutExchange());
    }
}
