package com.tutorial.start.controller;

import com.tutorial.util.ConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/3/23  14:53
 * 读取属性配置
 */
@RestController
public class DataBinderController {
    @Autowired
    private Environment environment;

    @Value("${server.port}")
    private Integer serverPort;

    /**
     * @param key {key:.+}解决通过url参数访问的时候小数点丢失的问题
     * @return
     */
    @RequestMapping("/readProperty/{key:.+}")
    public String environmentWay(@PathVariable(value = "key") String key){
        String value = environment.getProperty(key);
        System.out.println(value);
        return ConstantsUtil.SUCCESS;
    }





}
