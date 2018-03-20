package com.kafka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/3/20  15:34
 */
@RestController
public class TestController {

    @RequestMapping("/testKafka")
    public String test(){
        return "success";
    }
}
