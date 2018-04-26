package com.tutorial.quartz;

import com.tutorial.service.UserService;
import lombok.Data;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Jimmy. 2018/4/26  16:00
 */
@Data
public class CustomJob extends QuartzJobBean {
    @Autowired
    private UserService userService;

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        System.out.println("hello,"+name);
       // System.out.println(userService.findById(1L));
    }
}
