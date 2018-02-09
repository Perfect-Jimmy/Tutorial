package com.rabbitmq.configurer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Created by Jimmy. 2018/2/6  11:31
 */
//@Component
public class Receiver {

    @RabbitListener(queues = "hello.queue1")
    public String processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue1队列的消息：" + msg);
        return msg.toUpperCase();
    }

    @RabbitListener(queues = "hello.queue2")
    public void processMessage2(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自hello.queue2队列的消息：" + msg);
    }
}


