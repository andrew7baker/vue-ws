package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysDict;
import com.mycompany.myapp.util.SambaUtil;
import jcifs.smb.SmbException;
import org.jdbi.v3.core.Jdbi;
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

    public SmtService(SysOperationLogService sysOperationLogService, SysFileInfoService sysFileInfoService, CommonService commonService) {
        this.sysOperationLogService = sysOperationLogService;
        this.sysFileInfoService = sysFileInfoService;
        this.commonService = commonService;
    }

    /**
     * 抓取局域网对应IP 地址的日志
     */
    public void captureMachineData() throws MalformedURLException, UnknownHostException, SmbException {
        List<SysDict> list=commonService.getMachineList("SMT_MACHINE_CODE");
        //用 备注替代 IP地址
        SambaUtil sambaUtil = new SambaUtil(this.sysOperationLogService,this.sysFileInfoService, commonService);
        for(int i=0;i<list.size();i++){
            String url = ((SysDict)list.get(i)).getDescription();
            log.info("【url="+url+"】");
            if(url!=null)
                sambaUtil.checkRemoteSimpleUrl(url);
        }
    }
}
