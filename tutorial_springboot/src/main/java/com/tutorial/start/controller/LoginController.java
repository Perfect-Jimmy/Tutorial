package com.tutorial.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Jimmy. 2018/3/30  14:14
 */
@Controller
public class LoginController {

    @RequestMapping("/index")
    public String index(){
        return "login";
    }


    @RequestMapping("/login")
    public String login(@RequestParam(value = "userName")String userName,
                        @RequestParam(value = "password")String password, Model model){
        model.addAttribute("name", "world");
        if(userName.equals("jimmy") && password.equals("123456")){
            return "welcome";
        }else{
            return "error";
        }
    }
}
