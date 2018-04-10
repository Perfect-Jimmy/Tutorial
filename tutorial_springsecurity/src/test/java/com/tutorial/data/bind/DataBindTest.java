package com.tutorial.data.bind;

import com.tutorial.SecurityStart;
import com.tutorial.configurer.domain.ThreadPoolParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Jimmy. 2018/3/19  17:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityStart.class)
public class DataBindTest {

    /**
     * 绑定简单配置
     */
    @Test
    public void simpleDataBindTest(){
        ApplicationContext context = SpringApplication.run(SecurityStart.class);
        Binder binder = Binder.get(context.getEnvironment());
        ThreadPoolParam poolParam = binder.bind("spring.task.pool", Bindable.of(ThreadPoolParam.class)).get();
        System.out.println(poolParam.getCorePoolSize());
    }


    /**
     * 绑定List配置
     */
    @Test
    public void listDataBindTest(){
        ApplicationContext context = SpringApplication.run(SecurityStart.class);
        Binder binder = Binder.get(context.getEnvironment());
        List<String> colourParam = binder.bind("spring.colour.name", Bindable.listOf(String.class)).get();
        System.out.println(colourParam);
    }

    /**
     * 属性是否存在
     * true 存在
     */
    @Test
    public void readPropertyTest(){
        ApplicationContext context = SpringApplication.run(SecurityStart.class);
        System.out.println(context.getEnvironment().containsProperty("spring.colour.name"));
    }
}
