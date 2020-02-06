package com.mycompany.myapp.config;

import com.mycompany.myapp.service.CommonService;
import com.mycompany.myapp.util.SmtQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SMTQuartzConfig {

    private final CommonService commonService;

    public SMTQuartzConfig(CommonService commonService) {
        this.commonService = commonService;
    }


    @Bean
    public JobDetail teatQuartzDetail(){
        return JobBuilder.newJob(SmtQuartz.class).withIdentity("testQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger(){
        // 默认10分钟
        Integer minutes = 10;
        // 最小10分钟
        if(commonService.getSingleSysDict("SMT_SYN_INTERVAL")!=null){
            Integer SMT_SYN_INTERVAL = Integer.parseInt(commonService.getSingleSysDict("SMT_SYN_INTERVAL"));
            if(SMT_SYN_INTERVAL>10)
                minutes = SMT_SYN_INTERVAL;
        }

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInMinutes(minutes)    //设置时间周期单位分钟
            .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
            .withIdentity("testQuartz")
            .withSchedule(scheduleBuilder)
            .build();
    }
}
