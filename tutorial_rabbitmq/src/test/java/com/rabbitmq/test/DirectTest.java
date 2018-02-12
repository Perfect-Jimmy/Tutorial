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
    public void send(){
        directSender.send("hello direct 中文");
    }


    @Test
    public void convertAndSend(){
        Animal animal = null;
        for(Long i=1L;i<=10L;i++){
            animal = new Animal();
            animal.setId(i);
            animal.setName("convertAndSend rabbit");
            animal.setColor("convertAndSend white");
            directSender.convertAndSend(animal);
        }
       /* StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stopWatch.stop();
        stopWatch.getTotalTimeMillis()*/
    }


    @Test
    public void convertSendAndReceive(){
        Animal animal = null;
        for(Long i=1L;i<=10L;i++){
            animal = new Animal();
            animal.setId(i);
            animal.setName("convertSendAndReceive rabbit");
            animal.setColor("convertSendAndReceive white");
            directSender.convertSendAndReceive(animal);
        }
    }
}
