package com.rabbitmq.controller;

import com.rabbitmq.Animal;
import com.rabbitmq.configurer.Producer;
import com.tutorial.util.ConstantsUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jimmy. 2018/2/9  17:02
 */
@RestController
public class RabbitController {

    @Autowired
    private Producer producer;

//-------direct--------------------------------------------------------------------------------------------------

    @RequestMapping("/direct")
    public String directProducer(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
    //  producer.send_direct("direct producer");
        for(Animal animal:getAnimals("direct")){
            producer.convertAndSend_direct(animal);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        return ConstantsUtil.SUCCESS;
    }

//--------fanout--------------------------------------------------------------------------------------------------------

    @RequestMapping("/fanout")
    public String fanoutProducer(){
    //  producer.send_fanout("fanout producer");
       /* for(Animal animal:getAnimals("fanout")){
          //  producer.convertAndSend_fanout(animal);

        }*/
        Animal animal = new Animal();
        animal.setId(100L);
        producer.convertSendAndReceive_fanout(animal);
        return ConstantsUtil.SUCCESS;
    }

//--------topic----------------------------------------------------------------------------------------------------------------

    @RequestMapping("/topic")
    public String topicProducer(@RequestParam(value = "routingKey") String routingKey){
       // producer.send_topic(routingKey,"this is log");
        Animal animal = new Animal();
        animal.setId(100L);
        producer.convertAndSend_topic(routingKey,animal);
        return ConstantsUtil.SUCCESS;
    }





    private List<Animal> getAnimals(String name){
        List<Animal> list = Lists.newArrayList();
        Animal animal = null;
        for(Long i=1L;i<=10;i++){
            animal = new Animal();
            animal.setId(i);
            animal.setName(name);
            animal.setColor("white");
            list.add(animal);
        }
        return list;
    }
}
