package com.rabbitmq.configurer.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/1/30  17:12
 */
@Data
@Component
@ConfigurationProperties(prefix="spring.task.pool")
public class ThreadPoolParam {
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveSeconds;
    private int queueCapacity;
}