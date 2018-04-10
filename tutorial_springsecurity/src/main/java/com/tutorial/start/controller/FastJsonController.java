package com.tutorial.start.controller;

import com.tutorial.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/3/30  15:01
 */
@RestController
public class FastJsonController {

    @RequestMapping("fastJson")
    public User fastJson(){
        User user = new User();
        user.setUserName("jimmy");
        user.setPassword(null);
        return user;
    }
}
