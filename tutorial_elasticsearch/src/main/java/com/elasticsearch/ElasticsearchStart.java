package com.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Created by Jimmy. 2018/1/29  10:46
 */
@SpringBootApplication(exclude={MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class ElasticsearchStart {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchStart.class,args);
    }
}
