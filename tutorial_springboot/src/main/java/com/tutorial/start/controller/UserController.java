package com.tutorial.start.controller;

import com.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jimmy. 2018/4/9  10:54
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 跳转到user page
     * @return
     */
    @RequestMapping("userPage")
    public String userPage(){
       return "/thymeleaf/user";
    }
}
