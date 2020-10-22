package com.cenmo.mogu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Cenmo
 * @Date 2020-10-10 2020/10/10
 * FTP服务配置类
 */
//@ConfigurationProperties(prefix = "mogu.ftp")
//@Component
public class FtpProperties {
    private String ipAddress;
    private Integer port;
    private String userName;
    private String password;
    private String basePath;
    private String picBaseUrl;

    public String getAddress() {
        return ipAddress;
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

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setPicBaseUrl(String picBaseUrl) {
        this.picBaseUrl = picBaseUrl;
    }

    @Override
    public String toString() {
        return "FtpProperties{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", basePath='" + basePath + '\'' +
                ", picBaseUrl='" + picBaseUrl + '\'' +
                '}';
    }
}
