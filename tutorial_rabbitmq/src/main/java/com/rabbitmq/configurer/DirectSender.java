package com.rabbitmq.configurer;

import com.tutorial.util.RabbitExchangeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Created by Jimmy. 2018/2/9  17:52
 * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调
 * ReturnCallback接口用于实现消息发送到RabbitMQ交换器但无相应队列与交换器绑定时的回调
 */
@Component
public class DirectSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    //消息回调只能代表成功消息发送到RabbitMQ服务器,不能代表消息被成功处理和接受
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            LOGGER.info("消息发送成功:{}",correlationData);
        } else {
            LOGGER.info("消息发送失败:{}",cause);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.info("消息发送失败:{}",message.getMessageProperties().getCorrelationIdString());
    }

    /**
     * 发送消息,不需要实现任何接口,供外部调用
     * @param object
     */
    public void send(Object object){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        LOGGER.info("开始发送消息:{}",object);
        String response = rabbitTemplate.convertSendAndReceive(RabbitExchangeType.DIRECT.name(), "key.direct", object, correlationId).toString();
        LOGGER.info("结束发送消息:{}",object);
        LOGGER.info("消费者响应:{},消息处理完成",response);
    }
}
