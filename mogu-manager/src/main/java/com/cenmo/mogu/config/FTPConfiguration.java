package com.cenmo.mogu.config;

/**
 * @author Cenmo
 * @Date 2020-10-10 2020/10/10
 * FTP服务配置类
 */
public class FTPConfiguration {
    private String IpAddress="192.168.117.129";
    private Integer port=21;
    private String userName="ftpuser";
    private String password="123456";
    private String basePath="/home/ftpuser/www/images";
    private String picBaseUrl="http://192.168.117.129/www/images";

    public String getAddress() {
        return IpAddress;
    }

    public Integer getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getBasePath() {
        return basePath;
    }

    public String getPicBaseUrl() {
        return picBaseUrl;
    }
}
