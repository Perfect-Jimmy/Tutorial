package com.tutorial.controller;

import com.tutorial.util.ConstantsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/4/17  15:57
 */
@RestController
public class TestController {

    @RequestMapping("hello")
    public String hello(){
        return ConstantsUtil.SUCCESS;
    }
}
