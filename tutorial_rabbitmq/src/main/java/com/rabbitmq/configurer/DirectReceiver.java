package com.rabbitmq.configurer;

import com.rabbitmq.Animal;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jimmy. 2018/2/9  23:04
 */
@Component
public class DirectReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectReceiver.class);

    /*@RabbitListener(queues = "direct_queue")
    public String process1(String msg) {
        LOGGER.info("接收到direct_queue的消息：" + msg);
        return msg.toUpperCase();
    }*/

    @RabbitListener(queues = "direct_queue")
    public String process2(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        LOGGER.info("接收到direct_queue的消息：" + animal);
        TimeUnit.SECONDS.sleep(10);
        LOGGER.info("消息Id {}",message.getMessageProperties().getDeliveryTag());
        // 确认消息已经消费成功
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        return animal.toString();
    }
}
