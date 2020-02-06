package com.mycompany.myapp.config;

import com.mycompany.myapp.service.SysDictTypeService;
import com.mycompany.myapp.util.SmtQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SMTQuartzConfig {

    private final SysDictTypeService sysDictTypeService;

    public SMTQuartzConfig(SysDictTypeService sysDictTypeService) {
        this.sysDictTypeService = sysDictTypeService;
    }

    @Bean
    public JobDetail teatQuartzDetail(){
        return JobBuilder.newJob(SmtQuartz.class).withIdentity("testQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//            .withIntervalInSeconds(30)  //设置时间周期单位秒
            .withIntervalInMinutes(10)    //设置时间周期单位分钟
            .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
            .withIdentity("testQuartz")
            .withSchedule(scheduleBuilder)
            .build();
    }
}
