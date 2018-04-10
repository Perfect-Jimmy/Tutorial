package com.tutorial.start.controller;

import com.tutorial.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jimmy. 2018/4/9  10:54
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 跳转到book page
     * @return
     */
    @RequestMapping("bookPage")
    public String bookPage(){
       return "/thymeleaf/book";
    }
}
