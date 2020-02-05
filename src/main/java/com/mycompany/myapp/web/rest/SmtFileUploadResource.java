package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Production;
import com.mycompany.myapp.service.FileStorageService;
import com.mycompany.myapp.service.ProductionService;
import com.mycompany.myapp.util.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SmtFileUploadResource controller
 */
@RestController
@RequestMapping("/api/smt-file-upload")
public class SmtFileUploadResource {

    private final Logger log = LoggerFactory.getLogger(SmtFileUploadResource.class);

    //https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/

    @Autowired
    private FileStorageService fileStorageService;


    private final ProductionService productionService;

    public SmtFileUploadResource(ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);
        log.info("fileName="+fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName)
            .toUriString();
        readSMmeXdr();

        return new UploadFileResponse(fileName, fileDownloadUri,
            file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    /**
     * 解析文件入库
     */
    public void  readSMmeXdr(){
        BufferedReader br;
        InputStreamReader inputStreamReader;
        FileInputStream fileInputStream;


        String uri = "tmp-fileupload";
        List list = new ArrayList();
        //计时
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
        TimeZone t = sdf.getTimeZone();
        t.setRawOffset(0);
        sdf.setTimeZone(t);
        Long startTime = System.currentTimeMillis();
        //计时

        File mrefile = new File(uri);
        File[] array = mrefile.listFiles();
        for(int i=0;i<array.length;i++){
            String inFileName = array[i].getPath();
            File file = array[i];
            System.out.println("路径"+inFileName);
            try {
                fileInputStream = new FileInputStream(inFileName);
                inputStreamReader =new InputStreamReader(fileInputStream);
                br = new BufferedReader(inputStreamReader);
                String line = "";
                String srg[] = new String[26];  //初始化一下数组长度，为后面数据补空
                int j= 0;
                Production p = new Production();
                while((line=br.readLine())!=null){
                    if(j<26){
                        String[] str = line.split("\\,");
                        String value = line.split(": ")[1];
                        ;
                        if(j==0) p.setVersion(value);
                        if(j==1) p.setPowerTime(new Integer(value));
                        if(j==2) p.setPlaceTime(new Integer(value));
                        if(j==3) p.setWaitTime(new Integer(value));
                        if(j==4) p.setRunTime(new Integer(value));
                        if(j==5) p.setStopTime(new Integer(value));
                        if(j==6) p.setIdleTime(new Integer(value));
                        if(j==7) p.setInWaitTime(new Integer(value));
                        if(j==8) p.setOutWaitTime(new Integer(value));
                        if(j==9) p.setTransTime(new Integer(value));
                        if(j==10) p.setWrongStopTime(new Integer(value));
                        if(j==11) p.setErrorStopTIme(new Integer(value));
                        if(j==12) p.setWrongStopCount(new Integer(value));
                        if(j==13) p.setErrorStopCount(new Integer(value));
                        if(j==14) p.setPanelInCount(new Integer(value));
                        if(j==15) p.setPanelOutCount(new Integer(value));
                        if(j==16) p.setPanelCount(new Integer(value));
                        if(j==17) p.setpCBCount(new Integer(value));
                        if(j==18) p.setErrorPcb(new Integer(value));
                        if(j==19) p.setSkipPCB(new Integer(value));
                        if(j==20) p.setOperationRate(Float.valueOf(value.trim()));
                        if(j==21) p.setPlacementRate(Float.valueOf(value.trim()));
                        if(j==22) p.meanTime(Float.valueOf(value.trim()));
                        if(j==23) p.setRealTime(Float.valueOf(value.trim()));
                        if(j==24) p.setTransferTime(Float.valueOf(value.trim()));
                        if(j==25) p.placeCount(new Integer(value));
                        System.out.println("j="+j+":line="+line+";value="+value);
                        j++;
                        productionService.save(p);
                    }
                }
//                analyzeXdrService.insertSMmeXdr(list);
                System.out.println("上传成功！！");
                Long endTime = System.currentTimeMillis();
                System.out.println("用时：" + sdf.format(new Date(endTime - startTime)));
                br.close();
                inputStreamReader.close();
                fileInputStream.close();
//                boolean flag = file.delete();
//                if (flag){
//                    System.out.println("文件删除成功");
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

