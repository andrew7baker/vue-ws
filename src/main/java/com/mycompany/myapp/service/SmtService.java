package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysDict;
import com.mycompany.myapp.util.SambaUtil;
import jcifs.smb.SmbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.List;

@Service
@Transactional
public class SmtService {

    private final Logger log = LoggerFactory.getLogger(SmtService.class);

    private final SysOperationLogService sysOperationLogService;
    private final SysFileInfoService sysFileInfoService;
    private final CommonService commonService;
    private final ProductionService productionService;
    private final SysRelationService sysRelationService;


    public SmtService(SysOperationLogService sysOperationLogService, SysFileInfoService sysFileInfoService, CommonService commonService, ProductionService productionService, SysRelationService sysRelationService) {
        this.sysOperationLogService = sysOperationLogService;
        this.sysFileInfoService = sysFileInfoService;
        this.commonService = commonService;
        this.productionService = productionService;
        this.sysRelationService = sysRelationService;
    }

    /**
     * 抓取局域网对应IP 地址的日志
     */
    public void captureMachineData() throws MalformedURLException, UnknownHostException, SmbException {
        List<SysDict> list=commonService.getMachineList("SMT_MACHINE_TYPE");
        //用 备注替代 IP地址
        SambaUtil sambaUtil = new SambaUtil(this.sysOperationLogService,this.sysFileInfoService, commonService, productionService, sysRelationService);
        long curTime =System.currentTimeMillis();
        for(int i=0;i<list.size();i++){
            String url = ((SysDict)list.get(i)).getDescription();
            log.info("【机器code="+((SysDict)list.get(i)).getCode()+";url="+((SysDict)list.get(i)).getDescription()+"】");
            if(url!=null)
                sambaUtil.checkRemoteSimpleUrl((SysDict)list.get(i));
        }
        log.info("【定时任务下拉数据共耗时:"+(System.currentTimeMillis()-curTime)+"毫秒】");
    }
}
