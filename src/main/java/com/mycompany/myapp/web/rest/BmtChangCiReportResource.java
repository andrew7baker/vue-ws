package com.mycompany.myapp.web.rest;

import com.alibaba.fastjson.JSON;
import com.mycompany.myapp.domain.BmtPayRecord;
import com.mycompany.myapp.domain.Production;
import org.apache.commons.net.ftp.*;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.mycompany.myapp.util.ResponseJsonUtils;


/**
 * BmtChangCiReportResource controller
 */
@RestController
@RequestMapping("/api/bmt-chang-ci-report")
public class BmtChangCiReportResource {

    private final Logger log = LoggerFactory.getLogger(BmtChangCiReportResource.class);



    /**
    * POST bmtReportResource
    */
    @PostMapping("/bmt-report-resource")
    public String bmtReportResource() {
        long startTimeMillis = System.currentTimeMillis();
        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/bmt?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        log.info("jdbi"+jdbi);

        String jsonStr = "[{\"JACKIE_ZHANG\":\"张学友\"},{\"ANDY_LAU\":\"刘德华\"},{\"LIMING\":\"黎明\"},{\"Aaron_Kwok\":\"郭富城\"}]" ;
        //做5次测试


        try (FileWriter file = new FileWriter("payReport.json")) {

            file.write("");
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String FTP_HOST  = "";
        int FTP_PORT = 21;
        String FTP_USER  = "your_username";
        String FTP_PASS = "your_password";
        String FTP_REMOTE_DIRECTORY = "/";
        String FTP_LOCAL_DOWNLOAD_DIR = "d:/download/";

        List<String> list =jdbi.withHandle(handle ->
            handle.createQuery("select value from SYS_CONFIG where code = ?")
                .bind(0, "FTP_USER")
                .mapTo(String.class)
                .list());
        FTP_USER  = list.size()>0?list.get(0):"";

        list =jdbi.withHandle(handle ->
            handle.createQuery("select value from SYS_CONFIG where code = ?")
                .bind(0, "FTP_PASS")
                .mapTo(String.class)
                .list());
        FTP_PASS  = list.size()>0?list.get(0):"";

        list =jdbi.withHandle(handle ->
            handle.createQuery("select value from SYS_CONFIG where code = ?")
                .bind(0, "FTP_REMOTE_DIRECTORY")
                .mapTo(String.class)
                .list());
        FTP_REMOTE_DIRECTORY  = list.size()>0?list.get(0):"";

        list =jdbi.withHandle(handle ->
            handle.createQuery("select value from SYS_CONFIG where code = ?")
                .bind(0, "FTP_HOST")
                .mapTo(String.class)
                .list());
        FTP_HOST  = list.size()>0?list.get(0):"";

//  https://gist.github.com/nabil-hassan/7e7c6700eee628a7c491
        FTPClient ftp = new FTPClient();
        PrintWriter writer = new PrintWriter(System.out);
        try {

            // Connect/login.
            System.out.println(MessageFormat.format("Connecting to ftp host: {0} on port: {1}",
                FTP_HOST, FTP_PORT));
            ftp.connect(FTP_HOST, FTP_PORT);
            boolean success =ftp.login(FTP_USER, FTP_PASS);
            log.info("\n 连接返回 {}", success);
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("不能连接主机 ====== " + FTP_HOST);
            }
            ftp.changeWorkingDirectory(FTP_REMOTE_DIRECTORY);
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("不能改变FTP目录 ====== " + FTP_REMOTE_DIRECTORY);
            }
            int replyCode = ftp.getReplyCode();
            FTPFile[] files = ftp.listFiles();
            System.out.println("files="+ Arrays.toString(files));

            // Iteratively download all files in the directory.
            for (FTPFile file : ftp.listFiles()) {
                System.out.println(MessageFormat.format("Transferring remote file: {0} to local directory: {1}",
                    file.getName(), FTP_LOCAL_DOWNLOAD_DIR));
                File target = new File(FTP_LOCAL_DOWNLOAD_DIR + file.getName());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(target));
                ftp.retrieveFile(file.getName(), outputStream);
                outputStream.close();

                if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                    System.out.println(MessageFormat.format("Download for file: {0} failed", file.getName()));
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }finally {
            // Calculate time taken
            long endTimeMillis = System.currentTimeMillis();
            long totalTimemillis = endTimeMillis - startTimeMillis;
            log.debug("\n FTP耗时: {}  ", totalTimemillis);
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "bmtReportResource";
    }

    @GetMapping("/bmt-report")
    public String bmtGetReport() {
        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("changDiName","2号场地周四");

        Map<String, Object> payChangCi1 = new HashMap<String, Object>();
        payChangCi1.put("changCiName","12月19日");

        Map<String, Object> payRecord = new HashMap<String, Object>();
        payRecord.put("payPersonName","93社员");
        payRecord.put("payAmount","35");
        Map<String, Object> payRecord1 = new HashMap<String, Object>();
        payRecord1.put("payPersonName","老李");
        payRecord1.put("payAmount","35");
        payChangCi1.put("payRecords",new Object[]{payRecord,payRecord1});
        data1.put("payChangCis",new Object[]{payChangCi1});

        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("changDiName","5号场地周日");
        data.put("payChangDis",new Object[]{data1,data2});
        String s = JSON.toJSONString(data);


        s ="{'data': [['', 'Kia', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],['2016', 10, 11, 12, 13, 15, 16]]}";

        return s;
    }

    @GetMapping("/hansontable")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        String[] arr = {"", "Kia", "Nissan", "Toyota", "Honda", "Mazda", "Ford"};
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
        log.info("jdbi"+jdbi);
//        List<Production> list =jdbi.withHandle(handle ->
//            handle.createQuery("select * from production ")
//                .mapToBean(Production.class)
//                .list());
//        log.info("list"+list);
        String[][] arrStr=new String[3][5];
        arrStr[0][0]="付款时间";
        arrStr[0][1]="付款人ID";
        arrStr[0][2]="付款人";
        arrStr[0][3]="金额";
        arrStr[0][4]="场次";
        arrStr[1][0]="1月2日";
        arrStr[1][1]="3";
        arrStr[1][2]="九三";
        arrStr[1][3]="35";
        arrStr[1][4]="四号场地";
        arrStr[2][0]="5";
        arrStr[2][1]="5";
        arrStr[2][2]="5";
        arrStr[2][3]="5";
        arrStr[2][4]="5";
        map.put("data", arrStr);
        return map;
    }

    @GetMapping("/getSmt")
    public Map<String, Object> getSmtMap() {
        Map<String, Object> map = new HashMap<>(3);
        String[] arr = {"", "Kia", "Nissan", "Toyota", "Honda", "Mazda", "Ford"};
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://tx:5432/smt?", "smt", "smt");
        log.info("jdbi"+jdbi);
        List<Production> list =jdbi.withHandle(handle ->
            handle.createQuery("select * from production ")
                .mapToBean(Production.class)
                .list());
        log.info("list"+list);
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

        map.put("data", arrStr);
        return map;
    }

}
