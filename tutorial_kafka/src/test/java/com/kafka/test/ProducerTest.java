package com.kafka.test;

import com.kafka.KafkaStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Jimmy. 2018/3/20  16:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaStart.class)
public class ProducerTest {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void producer(){
       // kafkaTemplate.send(topic, key, "this is test");
        kafkaTemplate.send("test-topic","this is test");
    }
}
