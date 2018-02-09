package com.rabbitmq.test;

import com.rabbitmq.Animal;
import com.rabbitmq.RabbitMQStart;
import com.rabbitmq.configurer.DirectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Jimmy. 2018/2/9  23:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQStart.class)
public class DirectTest {
    @Autowired
    private DirectSender directSender;

    @Test
    public void sendDirectMsg(){
        directSender.send("hello direct 中文");
    }


    @Test
    public void sendDirectPoJo(){
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("rabbit");
        animal.setColor("white");
        directSender.send(animal);
    }


}
