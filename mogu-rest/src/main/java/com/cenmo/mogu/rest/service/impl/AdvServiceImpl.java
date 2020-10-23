package com.cenmo.mogu.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.mapper.AdvMapper;
import com.cenmo.mogu.pojo.Adv;
import com.cenmo.mogu.rest.dao.JedisClient;
import com.cenmo.mogu.rest.service.AdvService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdvServiceImpl extends ServiceImpl<AdvMapper, Adv> implements AdvService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${mogu.redis.adv.indexAdvKey}")
	private String indexAdvKey;
	
	@Override
	public List<Adv> getAdvByCid(long cid) {
		//添加缓存处理
		try {
			String string = jedisClient.hget(indexAdvKey, cid+"");
			if(!StringUtils.isBlank(string)) {
				List<Adv> list = JSONObject.parseArray(string, Adv.class);
				return list; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("category_id",cid);
		List<Adv> list = baseMapper.selectByMap(map);
		
		try {
			String jsonString = JSON.toJSONString(list);
			jedisClient.hset(indexAdvKey, cid+"", jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public String getIndexAdvKey() {
		return indexAdvKey;
	}

	public void setIndexAdvKey(String indexAdvKey) {
		this.indexAdvKey = indexAdvKey;
	}
}
