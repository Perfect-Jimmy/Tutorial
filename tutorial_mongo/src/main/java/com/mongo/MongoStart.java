package com.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Created by Jimmy. 2018/1/24  16:04
 * 禁止springboot自动加载持久化bean
 */
@SpringBootApplication(exclude={JpaRepositoriesAutoConfiguration.class,DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class MongoStart {
    public static void main(String[] args) {
        SpringApplication.run(MongoStart.class,args);
    }
}
