package com.redis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/4/17  18:03
 */
@RestController
public class TestRedisController {

    @RequestMapping("/hello")
    public String helloRedis(){
        return "redis";
    }
}
