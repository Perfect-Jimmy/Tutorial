package com.tutorial.start.controller;

import com.tutorial.quartz.CustomJob;
import com.tutorial.quartz.service.QuartzJobService;
import com.tutorial.util.ConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/4/26  17:27
 */
@RestController
public class QuartzController {
    @Autowired
    private QuartzJobService quartzJobService;

    @RequestMapping("/addJob")
    public String addQuartzJob(){
        quartzJobService.addJob("myjob2","myjob", CustomJob.class,"myjob2Trigger","myjobTrigger", "0/3 * * * * ?","hha");
        return ConstantsUtil.SUCCESS;
    }
}
