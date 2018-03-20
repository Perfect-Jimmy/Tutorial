package com.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/3/20  16:21
 */
@Component
public class KafkaListen {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListen.class);

    @KafkaListener(topics = {"test-topic"})
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        LOGGER.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
    }
}
