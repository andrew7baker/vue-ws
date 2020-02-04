package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SysDict;
import com.mycompany.myapp.domain.SysFileInfo;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommonService {

    private final Logger log = LoggerFactory.getLogger(CommonService.class);

    /**
     * 判断是否需要上传 true为需要,false为不需要
     * @param fileName
     * @return
     */
    public boolean getSysFileInfoByName(String fileName){
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
        List<SysFileInfo> list =jdbi.withHandle(handle ->
            handle.createQuery("SELECT * from sys_file_info where file_name='"+fileName+"' ")
                .mapToBean(SysFileInfo.class)
                .list());
        return list.size()==0;
    }

    /**
     * 取单个公共参数设置的值
     * 如:
     * dicType = SMT_SHARE_FOLDER
     * dicType = SMT_SYN_INTERVAL
     * @return
     */
    public String getSingleSysDict(String paramCode){
        // TODO:数据源需要动态取参数
        String returnCode = "";
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
        log.info("jdbi"+jdbi);
        List<SysDict> list =jdbi.withHandle(handle ->
            handle.createQuery("SELECT d.* from sys_dict d join  sys_dict_type  t on d.dic_type_id=t.id and t.code='"+paramCode+"' ")
                .mapToBean(SysDict.class)
                .list());
        log.info("list"+list);

        if(list.size()>0)
            returnCode = ((SysDict)list.get(0)).getCode();
        return returnCode;
    }

    /**
     * 从字典表取机器列表列表
     * dicType = MACHINE_CODE
     */
    public List<SysDict> getMachineList(String paramCode){
        // TODO:数据源需要动态取参数
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
        log.info("jdbi"+jdbi);
        List<SysDict> list =jdbi.withHandle(handle ->
            handle.createQuery("SELECT d.* from sys_dict d join  sys_dict_type  t on d.dic_type_id=t.id and t.code='"+paramCode+"' ")
                .mapToBean(SysDict.class)
                .list());
        log.info("list"+list);
        return list;
    }



}
