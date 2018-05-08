package com.cloud;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
/**
 * Created by Jimmy. 2018/4/25  11:09
 * https://blog.wuwii.com/eureka-server.html
 */
@SpringBootApplication(exclude={JestAutoConfiguration.class,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,DruidDataSourceAutoConfigure.class})
//@EnableEurekaServer
public class CloudStart {

    public static void main(String[] args) {
        SpringApplication.run(CloudStart.class,args);
    }
}
