package com.tutorial.exception.util;

/**
 * Created by Jimmy. 2018/3/26  15:40
 * 自定义异常
 */
public class MyException extends RuntimeException{

    public MyException(String message) {
        super(message);
    }
}
