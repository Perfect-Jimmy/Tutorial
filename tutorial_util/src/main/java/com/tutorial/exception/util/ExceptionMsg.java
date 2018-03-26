package com.tutorial.exception.util;

import lombok.Data;

/**
 * Created by Jimmy. 2018/3/26  14:55
 * 全局异常返回信息
 */
@Data
public class ExceptionMsg {
    private String errorCode;
    private String errorMsg;
    private String requestUrl;
    private Object object;
}
