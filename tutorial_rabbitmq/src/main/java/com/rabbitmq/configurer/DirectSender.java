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

    /**
     * 当消息成功到达exchange的时候触发的ack回调
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            LOGGER.info("消息发送成功:{}",correlationData);
        } else {
            LOGGER.info("消息发送失败:{}",cause);
        }

    }

    /**
     * 当消息成功到达exchange,但是没有队列与之绑定的时候触发的ack回调
     * 用于实现消息发送到RabbitMQ交换器,但无相应队列与交换器绑定时的回调.
     * 基本上来说线程不可能出现这种情况,除非手动将已经存在的队列删掉,否则在测试阶段肯定能测试出来.
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
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

   /* rabbitTemplate.send(message); //发消息,参数类型为org.springframework.amqp.core.Message
      rabbitTemplate.convertAndSend(object); //转换并发送消息.将参数对象转换为org.springframework.amqp.core.Message后发送.
      这个是异步的.消息是否发送成功需要用到ConfirmCallback和ReturnCallback回调函数类确认.
      rabbitTemplate.convertSendAndReceive(message)//转换并发送消息,且等待消息者返回响应消息.
      这是一个RPC方法,当发送消息过后,该方法会一直阻塞在哪里等待返回结果,直到请求超时.可以通过配置spring.rabbitmq.template.reply-timeout来配置超时时间*/
}
