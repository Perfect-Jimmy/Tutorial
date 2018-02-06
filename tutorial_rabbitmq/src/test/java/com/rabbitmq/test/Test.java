package com.rabbitmq.test;

import com.rabbitmq.RabbitMQStart;
import com.rabbitmq.configuration.Sender;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Jimmy. 2018/2/6  11:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQStart.class)
public class Test {
    @Autowired
    private Sender sender;

    @org.junit.Test
    public void sendTest() throws Exception {
        String msg = new Date().toString();
        sender.send(msg);
        Thread.sleep(1000);
    }

}
