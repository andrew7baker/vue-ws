package com.mycompany.myapp.util;

import com.mycompany.myapp.domain.Production;
import com.mycompany.myapp.domain.SysDict;
import com.mycompany.myapp.domain.SysDictType;
import com.mycompany.myapp.service.SmtService;
import com.mycompany.myapp.service.SysDictTypeService;
import com.mycompany.myapp.web.rest.SysDictTypeResource;
import org.jdbi.v3.core.Jdbi;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SmtQuartz extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(SmtQuartz.class);


    private final SmtService smtService;

    public SmtQuartz(SmtService smtService) {
        this.smtService = smtService;
    }

    /**
     * 执行定时任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("quartz task "+new Date());
//        Optional<SysDictType> sysDictType = sysDictTypeService.findOne(3051l);
//        System.out.println(sysDictType.get().getCode());
//        log.info("quartz task "+sysDictType.get().getCode());

        smtService.captureMachineData();

        System.out.println("quartz task "+new Date());
    }
}
