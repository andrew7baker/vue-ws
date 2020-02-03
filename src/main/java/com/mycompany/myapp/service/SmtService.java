package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysDict;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SmtService {

    private final Logger log = LoggerFactory.getLogger(SmtService.class);

    /**
     * 从字典表取机器列表
     * dicType = MACHINE_CODE
     */
    public List<SysDict> getMachineList(){
        // TODO:数据源需要动态取参数
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
        log.info("jdbi"+jdbi);
        List<SysDict> list =jdbi.withHandle(handle ->
            handle.createQuery("SELECT d.* from sys_dict d join  sys_dict_type  t on d.dic_type_id=t.id and t.code='MACHINE_CODE' ")
                .mapToBean(SysDict.class)
                .list());
        log.info("list"+list);
        return list;
    }

    /**
     * 抓取局域网对应IP 地址的日志
     */
    public void captureMachineData(){
        List<SysDict> list=this.getMachineList();
        //用 备注替代 IP地址
        for(int i=0;i<list.size();i++){
            String ip = ((SysDict)list.get(i)).getDescription();
            log.info("ip="+ip);
        }

    }

}
