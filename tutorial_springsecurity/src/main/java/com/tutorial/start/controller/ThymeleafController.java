package com.tutorial.start.controller;

import com.tutorial.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jimmy. 2018/3/30  14:07
 */
@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

    @RequestMapping("/hello")
    public String helloThymeleaf(Model model){
        User user = new User();
        user.setUserName("Jack");
        model.addAttribute("user", user);
        return "/thymeleaf/helloThymeleaf";
    }

}
