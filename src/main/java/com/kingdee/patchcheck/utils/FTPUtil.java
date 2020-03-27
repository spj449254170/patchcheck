package com.kingdee.patchcheck.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * Created by Administrator on 2019\3\9 0009.
 */
public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    //利用PropertiesUtil工具类来读取xml配置文件

    private static String ftpIp=ReadProperties.getValue("ftp.properties", "host");
    /* = PropertiesUtil.getProperty("ftp.server.ip")*/

    private static String ftpUser=ReadProperties.getValue("ftp.properties", "username");
    /* = PropertiesUtil.getProperty("ftp.user")*/

    private static String ftpPass=ReadProperties.getValue("ftp.properties", "password");
    /*= PropertiesUtil.getProperty("ftp.pass");*/

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    //对外开放的上传到ftp服务器的方法
    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile("img", fileList);
        logger.info("开始连接ftp服务器，结束上传，上传结果:{}", result);

        return result;
    }

    //不对外开放的上传ftp服务器方法
    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;
        //创建一个字节输出流
        FileInputStream fis = null;
        //连接文件服务器
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                InetAddress inetAddress =  ftpClient.getPassiveLocalIPAddress();

                //对发送的文件的一些配置
               /* ftpClient.changeWorkingDirectory(remotePath);
                //传输的字节数
                ftpClient.setBufferSize(10240000);
                //按照什么编码传输
                ftpClient.setControlEncoding("UTF-8");*/
                //按照二级制数传输，可以防止乱码问题
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                //开始向FTP服务器传输
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException e) {
                logger.error("上传文件异常", e);
                e.printStackTrace();
            } finally {
                //释放资源
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    //连接ftp服务器方法
    private boolean connectServer(String ip, int port, String user, String pwd) {

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常", e);
        }
        return isSuccess;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
