package com.tutorial.start.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Jimmy. 2018/3/30  14:14
 */
@Controller
//@PreAuthorize("hasRole('ADMIN')") //有ROLE_USER权限的用户可以访问
public class LoginController {

    /**
     * 自定义登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 自定义首页
     * @return
     */
    @RequestMapping(value = {"/"})
    public String index(){
        return "index";
    }

    /**
     * 自定义主页
     * @return
     */
    @GetMapping(value = {"/home"})
    public String home() {
        return "/home";
    }

    /**
     * session 失效跳转
     * @return
     */
    @GetMapping("/sessionInvalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String sessionInvalid(){
        return "/sessionInvalid";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }

   /* @GetMapping(value = {"/home","/"})
    public String home() {
        return "/home";
    }*/
}
