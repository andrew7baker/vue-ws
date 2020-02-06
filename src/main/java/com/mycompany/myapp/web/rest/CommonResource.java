package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Production;
import com.mycompany.myapp.service.CommonService;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommonResource controller
 */
@RestController
@RequestMapping("/api/common")
public class CommonResource {

    private final Logger log = LoggerFactory.getLogger(CommonResource.class);

    private final CommonService commonService;

    public CommonResource(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
    * GET defaultAction
    */
    @GetMapping("/default-action")
    public String defaultAction() {
        return "defaultAction";
    }

    @GetMapping("/getMachineList")
    public ResponseEntity getMachineList() {
        return ResponseEntity.ok(this.commonService.getMachineList("SMT_MACHINE_CODE"));
    }



    /**
     * 获取列表产品列表
     * @return
     * https://www.hangge.com/blog/cache/detail_2484.html
     */
    @PostMapping("/getSmtList")
    public ResponseEntity getSmtList(@RequestBody Map<String, Object> params) {
        if(params!=null)
            log.info("prodName="+params.get("prodName").toString()+";machineCode="+params.get("machineCode").toString());
        Map<String, Object> map = new HashMap<>(3);
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
        log.info("jdbi"+jdbi);
        List<Production> list =jdbi.withHandle(handle ->
            handle.createQuery("select *  from production ")
                .mapToBean(Production.class)
                .list());
        log.info("list"+list);
        Production p =(Production)list.get(0);
        String s = p.getPowerTime()+"";
        log.info("p.getPowerTime()="+p.getPowerTime()+";s="+s+";list");
        Map a = new HashMap<>();
        a.put("data",list );
        return ResponseEntity.ok(a);
    }

}
