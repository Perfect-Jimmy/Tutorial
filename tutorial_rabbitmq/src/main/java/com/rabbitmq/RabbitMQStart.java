package com.rabbitmq;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Created by Jimmy. 2018/2/6  11:14
 */
@SpringBootApplication(exclude={MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,DruidDataSourceAutoConfigure.class})
public class RabbitMQStart {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQStart.class,args);
    }
}
