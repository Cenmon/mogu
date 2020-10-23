package com.cenmo.mogu.rest.controller;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache/sync")
public class RedisController {
	@Autowired
	private RedisService redisService;
	
	@GetMapping("/content/{cid}")
	public MoguResult syncContent(@PathVariable long cid) {
		return redisService.syncContent(cid);
	}
	
	@GetMapping("/itemCat")
	public MoguResult syncItemCat() {
		return redisService.syncItemCat();
	}
}
