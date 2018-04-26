package com.tutorial.quartz.service.impl;

import com.tutorial.quartz.service.QuartzJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jimmy. 2018/4/26  17:02
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public Date addJob(String jobName, String jobGroupName, Class jobClass, String triggerName, String triggerGroupName, String cronExpression,String str) {
        Scheduler scheduled =  schedulerFactoryBean.getScheduler();

        //job定义:任务执行类 任务名 任务组
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).usingJobData("name",str).build();

        //触发器构建
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        try {
            return scheduled.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
