package com.tutorial.start.controller;

import com.tutorial.quartz.CustomJob;
import com.tutorial.quartz.service.QuartzJobService;
import com.tutorial.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/4/26  17:27
 */
@RestController
public class QuartzController {
    private static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

    @Autowired
    private QuartzJobService quartzJobService;

    @RequestMapping("/addJob")
    public String addQuartzJob(@RequestParam(value = "name") String name){
        quartzJobService.addJob(name,"jobGroup", CustomJob.class,name,
                "triggerGroup", "0/10 * * * * ?",name);
        return ConstantsUtil.SUCCESS;
    }

    @RequestMapping("/modJob")
    public String modQuartzJob(@RequestParam(value = "name") String name){
        quartzJobService.modifyJob(name,"jobGroup", CustomJob.class,name,
                "triggerGroup", "0/5 * * * * ?",name);
        return ConstantsUtil.SUCCESS;
    }

    @RequestMapping("/delJob")
    public String delQuartzJob(@RequestParam(value = "name") String name){
        quartzJobService.deleteJob(name,"jobGroup");
        return ConstantsUtil.SUCCESS;
    }
}
