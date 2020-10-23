package com.cenmo.mogu.manager.controller;

import com.cenmo.mogu.common.vo.PicResult;
import com.cenmo.mogu.manager.service.UpPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/pic")
public class UpPicController {

	@Autowired
	private UpPicService upPicService;
	
	@RequestMapping("/upload")
	public PicResult upload(MultipartFile uploadFile) {
		PicResult result = upPicService.uploadPicture(uploadFile);
		return result;
	}
}
