package com.tutorial.start.controller;

import com.tutorial.quartz.CustomJob;
import com.tutorial.quartz.service.QuartzJobService;
import com.tutorial.util.ConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/4/26  17:27
 */
@RestController
public class QuartzController {
    @Autowired
    private QuartzJobService quartzJobService;

    @RequestMapping("/addJob")
    public String addQuartzJob(@RequestParam(value = "name") String str){
        quartzJobService.addJob(str,"myjob", CustomJob.class,str,
                "myjobTrigger", "0/3 * * * * ?",str);
        return ConstantsUtil.SUCCESS;
    }
}
