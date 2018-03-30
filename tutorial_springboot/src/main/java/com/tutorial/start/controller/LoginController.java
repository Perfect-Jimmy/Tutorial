package com.tutorial.start.controller;

import com.tutorial.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
                        @RequestParam(value = "password")String password, Model model, HttpServletRequest request){
        model.addAttribute("name", "world");
        User user = new User();
        user.setUserName("jimmy");
        if(userName.equals("jimmy") && password.equals("123456")){
            request.getSession().setAttribute("session_user",user);
            return "welcome";
        }else{
            return "error";
        }
    }
}
