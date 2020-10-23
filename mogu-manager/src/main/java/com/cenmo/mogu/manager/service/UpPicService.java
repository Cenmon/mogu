package com.cenmo.mogu.manager.service;

import com.cenmo.mogu.common.vo.PicResult;
import org.springframework.web.multipart.MultipartFile;


public interface UpPicService {
	public PicResult uploadPicture(MultipartFile uploadFile);
}
