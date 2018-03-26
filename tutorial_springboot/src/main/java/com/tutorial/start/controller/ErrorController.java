package com.tutorial.start.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/3/26  11:39
 */
@RestController
public class ErrorController {

    @RequestMapping("errorTest")
    public String errorTest() throws Exception {
        throw new Exception("发生错误");
    }
}
