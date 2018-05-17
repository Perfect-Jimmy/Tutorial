package com.solr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/5/17  15:12
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public String test(){
        return "solr";
    }
}
