package com.rabbitmq.controller;

import com.rabbitmq.Animal;
import com.rabbitmq.configurer.DirectSender;
import com.tutorial.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/2/9  17:02
 */
@RestController
public class RabbitController {
    @Autowired
    private DirectSender directSender;

    @RequestMapping("directSender")
    public String directSender() {
        Animal animal = null;
        for(Long i=1L;i<=10L;i++){
            animal = new Animal();
            animal.setId(i);
            animal.setName("convertAndSend rabbit");
            animal.setColor("convertAndSend white");
            directSender.convertAndSend(animal);
        }
        return Constants.SUCCESS;
    }
}
