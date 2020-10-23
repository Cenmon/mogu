package com.cenmo.mogu.manager.service.impl;

import java.io.IOException;
import java.util.UUID;

import com.cenmo.mogu.common.utils.FtpUtil;
import com.cenmo.mogu.common.vo.PicResult;
import com.cenmo.mogu.manager.service.UpPicService;
import org.joda.time.DateTime;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@ConfigurationProperties(prefix = "mogu.ftp")
public class UpPicServiceImpl implements UpPicService {
	private String ipAddress;
	private Integer port;
	private String userName;
	private String password;
	private String basePath;
	private String picBaseUrl;
	
	@Override
	public PicResult uploadPicture(MultipartFile uploadFile) {
		if(uploadFile == null || uploadFile.isEmpty()) {
			return PicResult.error("上传图片为空");
		}
		//获取后缀名
		String originalFileName = uploadFile.getOriginalFilename();
		String postfix = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		//生成图片名称
		String imageName = UUID.randomUUID().toString() + postfix;
		
		//文件路径名称
		//joda-time的jar包中的方法，用于对时间的操作
		String filePath = new DateTime().toString("/yyyyMMdd");
		
		try {
			FtpUtil.uploadFile(ipAddress, port, userName, password, basePath,
										filePath, imageName, uploadFile.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return PicResult.error("图片上传异常");
		}
		//返回图片路径
		return PicResult.ok(basePath+filePath+"/"+imageName);
	}
}
