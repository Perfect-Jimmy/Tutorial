package com.tutorial.start.controller;

import com.tutorial.domain.Account;
import com.tutorial.exception.util.MyException;
import com.tutorial.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/3/26  16:09
 */
@RestController
public class CustomExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionController.class);

    @Autowired
    private AccountService accountService;

    /**
     * validated 参数绑定异常
     * @param account
     * @return
     */
    @RequestMapping(value = "account",method = RequestMethod.POST)
    public String saveAccount(@Validated @RequestBody Account account){
        System.out.println("-============================");
        logger.info("account save");
        accountService.saveAccount(account);
        return "success";
    }

    /**
     * 自定义异常
     * @return
     * @throws MyException
     */
    @RequestMapping("/customException")
    public String customException() throws MyException {
        throw new MyException("发生错误2");
    }
}
