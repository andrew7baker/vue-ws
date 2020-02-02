package com.mycompany.myapp.util;

import com.mycompany.myapp.web.rest.AccountResource;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class SmtQuartz extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(SmtQuartz.class);
    /**
     * 执行定时任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("quartz task "+new Date());
        System.out.println("quartz task "+new Date());
    }
}
