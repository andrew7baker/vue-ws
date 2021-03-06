package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Production;
import com.mycompany.myapp.domain.SysFileInfo;
import com.mycompany.myapp.service.CommonService;
import com.mycompany.myapp.util.pojo.page.LayuiPageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.generic.GenericType;
import org.jdbi.v3.core.mapper.JoinRow;
import org.jdbi.v3.core.mapper.JoinRowMapper;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private Jdbi jdbi;

    public CommonResource(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * 获取列表产品列表
     * @return
     * https://www.hangge.com/blog/cache/detail_2484.html
     */
//    @PostMapping("/getSmtList")
//    public ResponseEntity getSmtList(@RequestBody Map<String, Object> params) {
//        if(params!=null)
//            log.info("prodName="+params.get("prodName").toString()+";machineCode="+params.get("machineCode").toString());
//        Map<String, Object> map = new HashMap<>(3);
////        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
//        List<Production> list =jdbi.withHandle(handle ->
//            handle.createQuery("select *  from production ")
//                .mapToBean(Production.class)
//                .list());
////        log.info("list"+list);
//        Production p =(Production)list.get(0);
//        String s = p.getPowerTime()+"";
//        log.info("p.getPowerTime()="+p.getPowerTime()+";s="+s+";list");
//        Map a = new HashMap<>();
//        a.put("data",list );
//        return ResponseEntity.ok(a);
//    }

    /**
     * 110 机器
     * 111simple-query.html
     * @return
     */
    @PostMapping("/getReport")
    public ResponseEntity getReport(@RequestBody Map<String, Object> params) {
        int pagesize  = Integer.parseInt(params.get("pagesize").toString()) ;
        int currentpage = Integer.parseInt(params.get("currentpage").toString()) - 1 ;
        log.info("【currentpage="+currentpage);
        String sql = "";
        String sqlPage = "";
        if(params.get("machineCode")!="")
            sql = " and f.machine_code='"+params.get("machineCode").toString()+"'";
        if(params.get("prodName")!="")
            sql = sql+" and f.file_name LIKE '%"+params.get("prodName").toString()+"%'";
        sqlPage = sql+" limit " + pagesize +" offset "+currentpage*pagesize;


        log.info(" 翻页SQL = "+sql);
        String finalSqlCount = "SELECT count(*) " +
            " from (SELECT r.*,p.* prd_id FROM sys_relation r join production p  on \n" +
            "p.id=r.from_id) t  JOIN (SELECT f.*,d.name machine_name FROM sys_file_info f JOIN \n" +
            "(SELECT * FROM sys_dict WHERE dic_type_id IN(SELECT ID from sys_dict_type WHERE code='SMT_MACHINE_TYPE')) d\n" +
            "ON f.machine_code = d.code) f on t.to_id = f.id "
            +sql;

        log.info(" 翻页finalSqlCount = "+finalSqlCount);
        List<Integer> count =jdbi.withHandle(handle ->
            handle.select( finalSqlCount)
                .mapTo(Integer.class)
                .list());
        log.info("【count=】"+count.get(0));

        String finalSqlPage = "SELECT f.machine_code,f.machine_name,f.file_name,to_char(f.create_time , 'yyyy-mm-dd HH:MI:SS') crea_time,t.operation_rate " +
            ",t.placement_rate,t.mean_time,t.real_time,t.trans_time,t.place_count\n " +
            ",t.from_id prod_id "+
            " from (SELECT r.*,p.* prd_id FROM sys_relation r join production p  on \n" +
            "p.id=r.from_id) t  JOIN (SELECT f.*,d.name machine_name FROM sys_file_info f JOIN \n" +
            "(SELECT * FROM sys_dict WHERE dic_type_id IN(SELECT ID from sys_dict_type WHERE code='SMT_MACHINE_TYPE')) d\n" +
            "ON f.machine_code = d.code) f on t.to_id = f.id "
            +sqlPage
            ;
        log.info("【finalSqlPage=】\n"+finalSqlPage);
        List<Map<String, Object>> list =jdbi.withHandle(handle ->
            handle.createQuery( finalSqlPage)
                .mapToMap()
                .list());

        Map a = new HashMap<>();
        a.put("data",list );
        a.put("pagesize",20 );
        a.put("count",count.get(0) );

        return ResponseEntity.ok(a);
    }


    /**
     * file-detail.html 查看明显
     * @return
     */
    @PostMapping("/showProdDetail")
    public ResponseEntity showProdDetail(@RequestBody Map<String, Object> params) {
        log.info("prodId="+params.get("prodId"));
        int prodId  = Integer.parseInt(params.get("prodId").toString()) ;
//        int prodId = 12059;

        List<Production> list =jdbi.withHandle(handle ->
            handle.createQuery("select * from production where id = "+prodId)
                .mapToBean(Production.class)
                .list());
        Production p =(Production)list.get(0);
        String s = p.getPowerTime()+"";
        log.info("p.getPowerTime()="+p.getPowerTime()+";s="+s);
        String[][] arrStr=new String[26][2];
        arrStr[0][0]="H_CE";
        arrStr[0][1]="";
        arrStr[1][0]="Power Time";
        arrStr[1][1]=""+p.getPowerTime();
        arrStr[2][0]="Place Time";
        arrStr[2][1]= ""+p.getPlaceTime();
        arrStr[3][0]="Wait Time";
        arrStr[3][1]= ""+p.getWaitTime();
        arrStr[4][0]="Run Time";
        arrStr[4][1]= ""+p.getRunTime();
        arrStr[5][0]="Stop Time";
        arrStr[5][1]= ""+p.getStopTime();

        arrStr[6][0]="Idle Time";
        arrStr[7][0]="In Wait Time";
        arrStr[8][0]="Out Wait Time";
        arrStr[9][0]="Trans Time";
        arrStr[10][0]="Wrong Stop Time";
        arrStr[11][0]="Error Stop Time";
        arrStr[12][0]="Wrong Stop Count";
        arrStr[13][0]="Error Stop Count";
        arrStr[14][0]="Panel In Count";
        arrStr[15][0]="Panel Out Count";
        arrStr[16][0]="Panel Count";
        arrStr[17][0]="PCB Count";
        arrStr[18][0]="Skip PCB Count";
        arrStr[19][0]="Operation Rate";
        arrStr[20][0]="Placement Rate";
        arrStr[21][0]="Mean Time/PCB";
        arrStr[22][0]="Real Time/PCB";
        arrStr[23][0]="Transfer Time/PCB";
        arrStr[24][0]="Place Count";
        arrStr[25][0]="效率";
        arrStr[6][1]= ""+p.getIdleTime();
        arrStr[7][1]= ""+p.getInWaitTime();
        arrStr[8][1]= ""+p.getOutWaitTime();
        arrStr[9][1]= ""+p.getTransTime();
        arrStr[10][1]= ""+p.getWrongStopTime();
        arrStr[11][1]= ""+p.getErrorStopTIme();
        arrStr[12][1]= ""+p.getWrongStopCount();
        arrStr[13][1]= ""+p.getErrorStopCount();
        arrStr[14][1]= ""+p.getPanelOutCount();
        arrStr[15][1]= ""+p.getPanelOutCount();
        arrStr[16][1]= ""+p.getPanelCount();
        arrStr[17][1]= ""+p.getpCBCount();
        arrStr[18][1]= ""+p.getSkipPCB();
        arrStr[19][1]= ""+p.getOperationRate();
        arrStr[20][1]= ""+p.getPlacementRate();
        arrStr[21][1]= ""+p.getMeanTime();
        arrStr[22][1]= ""+p.getRealTime();
        arrStr[23][1]= ""+p.getTransferTime();
        arrStr[24][1]= ""+p.getPlaceCount();
        arrStr[25][1]= ""+p.getTheEfficiency();

        Map a = new HashMap<>();
        a.put("data", arrStr);
        return ResponseEntity.ok(a);
    }




    /**
     * 清空生产日志数据
     * @param params
     * @return
     */
    @PostMapping("/emptyRelation")
    public ResponseEntity
    emptyRelation(@RequestBody Map<String, Object> params) {
        jdbi.useHandle(handle -> {
            handle.execute("delete from sys_file_info");
            handle.execute("delete from sys_relation where id in (SELECT r.id FROM sys_relation r join production p  on p.id=r.from_id)");
            handle.execute("delete from production");
        });
        log.info("【清空数据】");
        Map<String, Object> map = new HashMap<>(3);
        Map a = new HashMap<>();
        return ResponseEntity.ok(a);
    }

    @ApiOperation("获取机器列表")
    @GetMapping("/getMachineList")
    public ResponseEntity getMachineList() {
        return ResponseEntity.ok(this.commonService.getMachineList("SMT_MACHINE_TYPE"));
    }

    /**
     *
     * @param machineCode 机器编码
     * @param prodName 产品名称
     * @param pagesize 每页大小
     * @param currentpage 当前页
     * @return
     */
    @ApiOperation("获取产品列表报告")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "machineCode", value = "机器编码", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "prodName", value = "产品名称", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "pagesize", value = "翻页大小", dataType = "int", paramType = "query",example = "20"),
        @ApiImplicitParam(name = "currentpage", value = "当前页", dataType = "int", paramType = "query",example = "1")
    })
    @PostMapping("/getReport2")
    public ResponseEntity getReport(String machineCode,String prodName,int pagesize,int currentpage) {
//        int intPageize  = Integer.parseInt(params.get("pagesize").toString()) ;
        currentpage = currentpage - 1 ;
        log.info("【currentpage="+currentpage);
        String sql = "";
        String sqlPage = "";
//        log.info("machineCode!=null"+machineCode!=null);
        if (machineCode != null ) {
            log.info("machineCode===="+machineCode);
            sql = " and f.machine_code='"+machineCode+"'";
        }

        if(prodName != null)
            sql = sql+" and f.file_name LIKE '%"+prodName+"%'";
        sqlPage = sql+" limit " + pagesize +" offset "+currentpage*pagesize;

        log.info(" 翻页SQL = "+sql);
        String finalSqlCount = "SELECT count(*) " +
            " from (SELECT r.*,p.* prd_id FROM sys_relation r join production p  on \n" +
            "p.id=r.from_id) t  JOIN (SELECT f.*,d.name machine_name FROM sys_file_info f JOIN \n" +
            "(SELECT * FROM sys_dict WHERE dic_type_id IN(SELECT ID from sys_dict_type WHERE code='SMT_MACHINE_TYPE')) d\n" +
            "ON f.machine_code = d.code) f on t.to_id = f.id "
            +sql;

        log.info(" 翻页finalSqlCount = "+finalSqlCount);
        List<Integer> count =jdbi.withHandle(handle ->
            handle.select( finalSqlCount)
                .mapTo(Integer.class)
                .list());
        log.info("【count=】"+count.get(0));

        String finalSqlPage = "SELECT f.machine_code,f.machine_name,f.file_name,to_char(f.create_time , 'yyyy-mm-dd HH:MI:SS') crea_time,t.operation_rate " +
            ",t.placement_rate,t.mean_time,t.real_time,t.trans_time,t.place_count\n " +
            ",t.from_id prod_id "+
            " from (SELECT r.*,p.* prd_id FROM sys_relation r join production p  on \n" +
            "p.id=r.from_id) t  JOIN (SELECT f.*,d.name machine_name FROM sys_file_info f JOIN \n" +
            "(SELECT * FROM sys_dict WHERE dic_type_id IN(SELECT ID from sys_dict_type WHERE code='SMT_MACHINE_TYPE')) d\n" +
            "ON f.machine_code = d.code) f on t.to_id = f.id "
            +sqlPage
            ;
        log.info("【finalSqlPage=】\n"+finalSqlPage);
        List<Map<String, Object>> list =jdbi.withHandle(handle ->
            handle.createQuery( finalSqlPage)
                .mapToMap()
                .list());

        Map a = new HashMap<>();
        a.put("data",list );
        a.put("pagesize",20 );
        a.put("count",count.get(0) );

        return ResponseEntity.ok(a);
    }

    /**
     *
     * @param machineCode
     * @param prodName
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation("获取产品列表报告(第三版)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "machineCode", value = "机器编码", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "prodName", value = "产品名称", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "翻页大小", dataType = "string", paramType = "query",example = "100"),
        @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "string", paramType = "query",example = "1")
    })
    @PostMapping("/v3/getReport")
    public ResponseEntity getReportV3(String machineCode,String prodName,String pageSize,String currentPage) {
        log.info("【pageSize=】"+pageSize);
        int intPageSize  = Integer.parseInt(pageSize) ;
        int intCurrentPage  = Integer.parseInt( currentPage) ;
        intCurrentPage = intCurrentPage - 1 ;
        log.info("【currentpage="+intCurrentPage);
        String sql = "";
        String sqlPage = "";
        if (machineCode != null&&!"".equals(machineCode) ) {
            log.info("machineCode===="+machineCode);
            sql = " and f.machine_code='"+machineCode+"'";
        }

        if(prodName != null&&!"".equals(prodName))
            sql = sql+" and f.file_name LIKE '%"+prodName+"%'";
        sqlPage = sql+" limit " + intPageSize +" offset "+intCurrentPage*intPageSize;

        log.info(" 翻页SQL = "+sql);
        String finalSqlCount = "SELECT count(*) " +
            " from (SELECT r.*,p.* prd_id FROM sys_relation r join production p  on \n" +
            "p.id=r.from_id) t  JOIN (SELECT f.*,d.name machine_name FROM sys_file_info f JOIN \n" +
            "(SELECT * FROM sys_dict WHERE dic_type_id IN(SELECT ID from sys_dict_type WHERE code='SMT_MACHINE_TYPE')) d\n" +
            "ON f.machine_code = d.code) f on t.to_id = f.id "
            +sql;

        log.info(" 翻页finalSqlCount = "+finalSqlCount);
        List<Integer> count =jdbi.withHandle(handle ->
            handle.select( finalSqlCount)
                .mapTo(Integer.class)
                .list());
        log.info("【count=】"+count.get(0));

        String finalSqlPage =
            "SELECT split_part(f.file_name,'_',2) program_name,\n" +
                "substr(f.machine_name, 0,3) line,\n" +
                "substr(f.machine_name, 3,10) machine,\n" +
                "substr(split_part(f.file_name,'_',(array_upper(regexp_split_to_array(f.file_name,'_'),1)-1)), 0, 9) create_date,\n" +
                "substr(split_part(f.file_name,'_',(array_upper(regexp_split_to_array(f.file_name,'_'),1)-1)), 9, 9) create_time,\n" +
                "substr(split_part(split_part(f.file_name,'_',array_upper(regexp_split_to_array(f.file_name,'_'),1)), '.', 1), 0,9) end_date,\n" +
                "substr(split_part(split_part(f.file_name,'_',array_upper(regexp_split_to_array(f.file_name,'_'),1)), '.', 1), 9,4)  end_time,\n" +
                "CASE \n" +
                " WHEN LENGTH(split_part(f.file_name,'_',(array_upper(regexp_split_to_array(f.file_name,'_'),1)-1)))=14 THEN\n" +
                "  date_part('h', \n" +
                "  TO_TIMESTAMP(\n" +
                "  split_part(split_part(f.file_name,'_',array_upper(regexp_split_to_array(f.file_name,'_'),1)), '.', 1)\n" +
                "  ,'YYYYMMDDHH24MI')::timestamp\n" +
                "  - \n" +
                "  TO_TIMESTAMP(\n" +
                "  split_part(f.file_name,'_',(array_upper(regexp_split_to_array(f.file_name,'_'),1)-1))\n" +
                "  ,'YYYYMMDDHH24MISS') ::timestamp \n" +
                "  )   \n" +
                " ELSE\n" +
                "  date_part('day', now()::timestamp - '2013-01-14 16:05'::timestamp)\n" +
                "END "+
                "total_time,t.panel_count,t.p_cb_count," +
                "case when panel_count=0 then null else round(t.p_cb_count/t.panel_count) end pingshu, \n" +
                "t.mean_time,t.trans_time,t.real_time,t.transfer_time,t.idle_time,\n" +
                "t.place_count,t.from_id prod_id,f.file_name,f.machine_code," +
                " jiagong_time,"+
                "CASE \n" +
                " WHEN LENGTH(split_part(f.file_name,'_',(array_upper(regexp_split_to_array(f.file_name,'_'),1)-1)))=14 THEN\n" +
                "  cast(date_part('h', \n" +
                "  TO_TIMESTAMP(\n" +
                "  split_part(split_part(f.file_name,'_',array_upper(regexp_split_to_array(f.file_name,'_'),1)), '.', 1)\n" +
                "  ,'YYYYMMDDHH24MI')::timestamp\n" +
                "  - \n" +
                "  TO_TIMESTAMP(\n" +
                "  split_part(f.file_name,'_',(array_upper(regexp_split_to_array(f.file_name,'_'),1)-1))\n" +
                "  ,'YYYYMMDDHH24MISS') ::timestamp \n" +
                "  ) - jiagong_time AS decimal(10,2) ) \n" +
                " ELSE\n" +
                "  cast(date_part('day', now()::timestamp - '2013-01-14 16:05'::timestamp) - jiagong_time AS decimal(10,2))  \n" +
                "END "+
                "tingji_time"+
            " from (SELECT " +
                "r.*,p.* prd_id FROM sys_relation r join " +
                "((select \n" +
                "cast(0.9*p_cb_count*(mean_time+transfer_time)/3600 AS decimal(10,2)) jiagong_time,\n" +
                "* FROM production GROUP BY ID)) p  on \n" +
            "p.id=r.from_id) t  JOIN (SELECT f.*,d.name machine_name FROM sys_file_info f JOIN \n" +
            "(SELECT * FROM sys_dict WHERE dic_type_id IN(SELECT ID from sys_dict_type WHERE code='SMT_MACHINE_TYPE')) d\n" +
            "ON f.machine_code = d.code) f on t.to_id = f.id " +
                "and f.file_name!='Total.log'"  //去除掉汇总的日志
            +sqlPage
            ;
        log.info("【finalSqlPage=】\n"+finalSqlPage);
        List<Map<String, Object>> list =jdbi.withHandle(handle ->
            handle.createQuery( finalSqlPage)
                .mapToMap()
                .list());

        Map a = new HashMap<>();
        a.put("data",list );
        a.put("pagesize",intPageSize );
        a.put("count",count.get(0) );

        return ResponseEntity.ok(a);
    }


    @ApiOperation("根据ID 获取产品明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "prodId", value = "产品ID", dataType = "int", paramType = "query",example = "12059")
    })
    @PostMapping("/showProdDetail2")
    public ResponseEntity showProdDetail2(int prodId) {
//        log.info("prodId="+params.get("prodId"));
//        int prodId  = Integer.parseInt(params.get("prodId").toString()) ;
//        int prodId = 12059;

        List<Production> list =jdbi.withHandle(handle ->
            handle.createQuery("select * from production where id = "+prodId)
                .mapToBean(Production.class)
                .list());
        Production p =(Production)list.get(0);
        String s = p.getPowerTime()+"";
        log.info("p.getPowerTime()="+p.getPowerTime()+";s="+s);
        String[][] arrStr=new String[26][2];
        arrStr[0][0]="H_CE";
        arrStr[0][1]="";
        arrStr[1][0]="Power Time";
        arrStr[1][1]=""+p.getPowerTime();
        arrStr[2][0]="Place Time";
        arrStr[2][1]= ""+p.getPlaceTime();
        arrStr[3][0]="Wait Time";
        arrStr[3][1]= ""+p.getWaitTime();
        arrStr[4][0]="Run Time";
        arrStr[4][1]= ""+p.getRunTime();
        arrStr[5][0]="Stop Time";
        arrStr[5][1]= ""+p.getStopTime();

        arrStr[6][0]="Idle Time";
        arrStr[7][0]="In Wait Time";
        arrStr[8][0]="Out Wait Time";
        arrStr[9][0]="Trans Time";
        arrStr[10][0]="Wrong Stop Time";
        arrStr[11][0]="Error Stop Time";
        arrStr[12][0]="Wrong Stop Count";
        arrStr[13][0]="Error Stop Count";
        arrStr[14][0]="Panel In Count";
        arrStr[15][0]="Panel Out Count";
        arrStr[16][0]="Panel Count";
        arrStr[17][0]="PCB Count";
        arrStr[18][0]="Skip PCB Count";
        arrStr[19][0]="Operation Rate";
        arrStr[20][0]="Placement Rate";
        arrStr[21][0]="Mean Time/PCB";
        arrStr[22][0]="Real Time/PCB";
        arrStr[23][0]="Transfer Time/PCB";
        arrStr[24][0]="Place Count";
        arrStr[25][0]="效率";
        arrStr[6][1]= ""+p.getIdleTime();
        arrStr[7][1]= ""+p.getInWaitTime();
        arrStr[8][1]= ""+p.getOutWaitTime();
        arrStr[9][1]= ""+p.getTransTime();
        arrStr[10][1]= ""+p.getWrongStopTime();
        arrStr[11][1]= ""+p.getErrorStopTIme();
        arrStr[12][1]= ""+p.getWrongStopCount();
        arrStr[13][1]= ""+p.getErrorStopCount();
        arrStr[14][1]= ""+p.getPanelOutCount();
        arrStr[15][1]= ""+p.getPanelOutCount();
        arrStr[16][1]= ""+p.getPanelCount();
        arrStr[17][1]= ""+p.getpCBCount();
        arrStr[18][1]= ""+p.getSkipPCB();
        arrStr[19][1]= ""+p.getOperationRate();
        arrStr[20][1]= ""+p.getPlacementRate();
        arrStr[21][1]= ""+p.getMeanTime();
        arrStr[22][1]= ""+p.getRealTime();
        arrStr[23][1]= ""+p.getTransferTime();
        arrStr[24][1]= ""+p.getPlaceCount();
        arrStr[25][1]= ""+p.getTheEfficiency();

        Map a = new HashMap<>();
        a.put("data", arrStr);
        return ResponseEntity.ok(a);
    }



}
