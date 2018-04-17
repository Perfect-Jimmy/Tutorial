package com.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Jimmy. 2018/4/17  15:51
 * 禁用mongo的自动配置
 */
@SpringBootApplication(exclude={MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@EnableAsync
public class OAuth2Start {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OAuth2Start.class,args);
    }

}
