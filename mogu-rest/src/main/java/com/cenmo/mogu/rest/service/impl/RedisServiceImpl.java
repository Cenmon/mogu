package com.cenmo.mogu.rest.service.impl;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.rest.dao.JedisClient;
import com.cenmo.mogu.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${mogu.redis.adv.indexAdvKey}")
	private String indexAdvKey;
	
	@Value("${mogu.redis.item.itemCatKey}")
	private String itemCatKey;
	
	@Override
	public MoguResult syncContent(long cid) {
		try {
			jedisClient.hdel(indexAdvKey, cid+"");
		} catch (Exception e) {
			MoguResult.error();
		}
		return MoguResult.ok();
	}

	@Override
	public MoguResult syncItemCat() {
		try {
			jedisClient.hdel(itemCatKey, "itemCatList");
		} catch (Exception e) {
			MoguResult.error();
		}
		return MoguResult.ok();
	}

	public String getIndexAdvKey() {
		return indexAdvKey;
	}

	public void setIndexAdvKey(String indexAdvKey) {
		this.indexAdvKey = indexAdvKey;
	}

	public String getItemCatKey() {
		return itemCatKey;
	}

	public void setItemCatKey(String itemCatKey) {
		this.itemCatKey = itemCatKey;
	}
}
