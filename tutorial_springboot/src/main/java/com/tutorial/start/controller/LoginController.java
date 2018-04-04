package com.tutorial.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jimmy. 2018/3/30  14:14
 */
@Controller
//@PreAuthorize("hasRole('ADMIN')") //有ROLE_USER权限的用户可以访问
public class LoginController {

    @RequestMapping("/index")
    public String index(){
        return "login";
    }


   /* @RequestMapping("/login")
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
    }*/


    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }


    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping(value = {"/home","/"})
    public String home() {
        return "/home";
    }
}
