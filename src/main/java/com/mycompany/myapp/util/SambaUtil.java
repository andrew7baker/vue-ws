package com.mycompany.myapp.util;

import com.mycompany.myapp.domain.SysOperationLog;
import com.mycompany.myapp.service.ProductionService;
import com.mycompany.myapp.service.SysOperationLogService;
import jcifs.UniAddress;
import jcifs.smb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.time.Instant;

public class SambaUtil {

    private final Logger log = LoggerFactory.getLogger(SambaUtil.class);

    private final SysOperationLogService sysOperationLogService;

    public SambaUtil(SysOperationLogService sysOperationLogService) {
        this.sysOperationLogService = sysOperationLogService;
    }

    /**
     * 从samba服务器上下载指定的文件到本地目录
     * @param remoteSmbFile   Samba服务器远程文件
     * @param localDir        本地目录的路径
     * @throws SmbException
     */

    public String downloadFileFromSamba(SmbFile remoteSmbFile, String localDir) throws SmbException {

        String message = "下载成功:"+remoteSmbFile.getPath();
        if (!remoteSmbFile.exists()) {
            message = "Samba服务器远程文件不存在"+remoteSmbFile.getPath();
            log.info(message);
        }
        if((localDir == null) || ("".equals(localDir.trim()))) {
            message = "本地目录路径不可以为空";
            log.info(message);
        }

        InputStream in = null;
        OutputStream out = null;

        try{
            //获取远程文件的文件名,这个的目的是为了在本地的目录下创建一个同名文件
            String remoteSmbFileName = remoteSmbFile.getName();

            //本地文件由本地目录，路径分隔符，文件名拼接而成
            File localFile = new File(localDir + File.separator + remoteSmbFileName);

            // 如果路径不存在,则创建
            File parentFile = localFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            //打开文件输入流，指向远程的smb服务器上的文件，特别注意，这里流包装器包装了SmbFileInputStream
            in = new BufferedInputStream(new SmbFileInputStream(remoteSmbFile));
            //打开文件输出流，指向新创建的本地文件，作为最终复制到的目的地
            out = new BufferedOutputStream(new FileOutputStream(localFile));

            //缓冲内存
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1){
                out.write(buffer);
                buffer = new byte[1024];
            }
        } catch (Exception e) {
            message = "下载过程中出现错误";
            log.info(message);
            e.printStackTrace();
        } finally {
            try  {
                out.close();
                in.close();
            } catch  (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    /**
     * 上传本地文件到Samba服务器指定目录
     * @param url
     * @param auth
     * @param localFilePath
     * @throws MalformedURLException
     * @throws SmbException
     */
    public void uploadFileToSamba(String url, NtlmPasswordAuthentication auth, String localFilePath) throws MalformedURLException, SmbException{
        if ((localFilePath == null) || ("".equals(localFilePath.trim()))) {
            System.out.println("本地文件路径不可以为空");
            return;
        }

        //检查远程父路径，不存在则创建
        SmbFile remoteSmbFile = new SmbFile(url, auth);
        String parent = remoteSmbFile.getParent();
        SmbFile parentSmbFile = new SmbFile(parent, auth);
        if (!parentSmbFile.exists()) {
            parentSmbFile.mkdirs();
        }

        InputStream in = null;
        OutputStream out = null;

        try{
            File localFile = new File(localFilePath);

            //打开一个文件输入流执行本地文件，要从它读取内容
            in = new BufferedInputStream(new FileInputStream(localFile));

            //打开一个远程Samba文件输出流，作为复制到的目的地
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteSmbFile));

            //缓冲内存
            byte [] buffer =  new   byte [1024];
            while  (in.read(buffer) != - 1 ) {
                out.write(buffer);
                buffer = new byte[1024];
            }

        } catch  (Exception e) {
            e.printStackTrace();

        } finally  {
            try  {
                out.close();
                in.close();
            } catch  (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param host
     * @throws UnknownHostException
     * @throws SmbException
     * @throws MalformedURLException
     */
    public void checkRemote(String host) throws UnknownHostException, SmbException, MalformedURLException{
        String username = "administrator";
        String password = "123";

        //samba服务器上的文件
        String filePath = "/share/20191214_140304_SFU605_M_303.txt";
//        String filePath = "/share/20191214_140304_SFU605_M_303.txt2";
        String demo1LocalDir = "./tmp-fileupload";
        log.info("host="+host);
        UniAddress ua = UniAddress.getByName(host);
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(host, username, password);
        //创建Smb文件,地址一定要使用smb://
        SmbFile remoteSmbFile = new SmbFile("smb://" + host + filePath, auth);
        String message = this.downloadFileFromSamba(remoteSmbFile, demo1LocalDir);
        SysOperationLog sysOperationLog = new SysOperationLog();
        sysOperationLog.setMessage(message);
        sysOperationLog.setCreateTime(Instant.now());

        log.info(message);
        sysOperationLogService.save(sysOperationLog);
    }

    public static void main(String[] args) throws UnknownHostException, SmbException, MalformedURLException {
        String host = "192.168.xxx.xxx";
        String username = "username";
        String password = "password";

        //samba服务器上的文件
        String filePath = "/a/b/xxx.pdf";
        String demo1LocalDir = "E:\\test\\samba";
        UniAddress ua = UniAddress.getByName(host);
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(host, username, password);
        SmbSession.logon(ua, auth);//验证是否能够成功登录
        //创建Smb文件,地址一定要使用smb://
        SmbFile remoteSmbFile = new SmbFile("smb://" + host + filePath, auth);
//        SambaUtil.downloadFileFromSamba(remoteSmbFile, demo1LocalDir);
//        System.out.println("download success");
//
//        //upload
//        String demo2LocalFile= "E:\\test\\samba\\xxx.pdf";
//        String sambaDir = "/a/b";
//        String filePathUpload = sambaDir + "/" + new File(demo2LocalFile).getName();
//        String url = "smb://" + host + filePathUpload;
//        SambaUtil.uploadFileToSamba(url, auth, demo2LocalFile);
//        System.out.println("upload success");
    }
}
