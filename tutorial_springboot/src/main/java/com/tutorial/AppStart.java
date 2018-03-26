package com.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * Created by Jimmy. 2018/1/24  15:51
 * 禁用mongo的自动配置
 */
@SpringBootApplication(exclude={MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
public class AppStart {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppStart.class,args);

       /* Binder binder = Binder.get(context.getEnvironment());
        List<CityParam> citys = binder.bind("spring.city", Bindable.listOf(CityParam.class)).get();
        System.out.println(citys);*/
    }
}
