package com.tutorial.configurer;

import com.tutorial.exception.util.ExceptionMsg;
import com.tutorial.exception.util.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jimmy. 2018/3/26  15:01
 * 统一异常处理
 * 使用 @RestControllerAdvice 代替 @ControllerAdvice,这样在方法上就可以不需要添加 @ResponseBody
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ExceptionMsg resultMsg = new ExceptionMsg();
        resultMsg.setErrorMsg(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            resultMsg.setErrorCode(HttpStatus.NOT_FOUND.toString());
        } else {
            resultMsg.setErrorCode("500");
        }
        return resultMsg;
    }

    //添加全局异常处理流程,根据需要设置需要处理的异常,本文以MethodArgumentNotValidException为例
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,
                                                MethodArgumentNotValidException exception){
        //exception.printStackTrace();
        //按需重新封装需要返回的错误信息
        //  List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        ExceptionMsg resultMsg = new ExceptionMsg();
        resultMsg.setRequestUrl(request.getRequestURI());
        //  List<ObjectError> errors = e.getBindingResult().getAllErrors();
        //解析原错误信息,封装后返回.此处返回非法的字段名称,原始值,错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
          /*  ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);*/
           /* System.out.println("field:"+error.getField());
            System.out.println("value:"+error.getRejectedValue());
            System.out.println("msg:"+error.getDefaultMessage());*/
            resultMsg.setErrorCode("301");
            resultMsg.setErrorMsg("参数不合法");
            // resultMsg.setErrorMsg(error.getDefaultMessage());
        }
            return resultMsg;
    }



    @ExceptionHandler(value = MyException.class)
    public Object jsonErrorHandler(HttpServletRequest request, MyException e){
        ExceptionMsg resultMsg = new ExceptionMsg();
        resultMsg.setRequestUrl(request.getRequestURL().toString());
        resultMsg.setErrorCode("301");
        resultMsg.setErrorMsg("自定义异常:参数不合法");
        return resultMsg;
    }
}
