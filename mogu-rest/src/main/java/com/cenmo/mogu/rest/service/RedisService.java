package com.cenmo.mogu.rest.service;


import com.cenmo.mogu.common.vo.MoguResult;

public interface RedisService {
	MoguResult syncContent(long cid);
	
	MoguResult syncItemCat();
}
