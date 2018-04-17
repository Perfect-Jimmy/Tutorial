package com.tutorial.start.controller;

import com.tutorial.domain.Account;
import com.tutorial.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jimmy. 2018/3/26  15:18
 */
@RestController
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/account")
    public String saveAccount(@Valid Account account, BindingResult result){
        if(result.hasErrors()){
            StringBuilder str = new StringBuilder();
            //获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            //获取本地Local,zh_CN
            Locale local = LocaleContextHolder.getLocale();
            //遍历错误字段获取错误信息
            for(FieldError error:fieldErrors){
                 //获取错误信息
                String errorMsg = messageSource.getMessage(error,local);
                str.append(error.getField()+":"+errorMsg+",");

            }
            return str.toString();
        }
            return ConstantsUtil.SUCCESS;
    }

}
