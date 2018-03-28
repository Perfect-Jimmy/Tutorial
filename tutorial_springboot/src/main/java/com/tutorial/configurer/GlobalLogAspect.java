package com.tutorial.configurer;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by jimmy on 2017/11/2.
 * http://blog.csdn.net/linxingliang/article/details/52324965
 * http://blog.csdn.net/zmken497300/article/details/53516764
 * 全局日志
 * @Aspect 定义切面类
 * @Pointcut 定义切点
 * @Before 在切入点开始处切入内容
 * @After  在切入点结尾处切入内容
 * @AfterReturning 在切入点return内容之后切入内容,可以对返回值进行处理
 * @Around 切入点前后切入内容,并自己控制何时执行切入点自身的内容
 * @AfterThrowing 处理当切入内容部分抛出异常之后的处理逻辑
 * @Order(-5) 值越小优先级越高,设置为负值确保第一个执行
 */
@Aspect
@Component
@Order(-5)
public class GlobalLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(GlobalLogAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 两个..代表所有子目录,最后括号里的两个..代表所有参数
     * execution(public * com.kfit.*.web..*.*(..))
     * ~第一个 * 代表任意修饰符及任意返回值.
     * ~第二个 * 任意包名
     * ~第三个 * 代表任意方法.
     * ~第四个 * 定义在web包或者子包
     * ~第五个 * 任意方法
     * ~ ..匹配任意数量的参数.
     */
    //定义切点
    @Pointcut("execution(public * com.tutorial..controller.*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        ThreadContext.put("logId", String.valueOf(System.currentTimeMillis()));
        // 接收到请求,记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("请求地址 : " + request.getRequestURL().toString());
        logger.info("HTTP METHOD : " + request.getMethod());
        // 获取真实的ip地址
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "_______"
                + joinPoint.getSignature().getName());
        logger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * /returning的值和doAfterReturning的参数名一致
     * @param object
     * @throws Throwable
     */
    @AfterReturning(returning = "object", pointcut = "logPointCut()")
    public void doAfterReturning(Object object) throws Throwable {
        // 处理完请求,返回内容(返回值太复杂时,打印的是物理存储空间的地址)
        logger.info("返回值 : " + object);
        logger.info("耗时毫秒 ThreadLocal" + (System.currentTimeMillis() - startTime.get()));
    }

    //切入点前后并自己控制何时执行切入点自身的内容
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        logger.info("耗时毫秒: " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}