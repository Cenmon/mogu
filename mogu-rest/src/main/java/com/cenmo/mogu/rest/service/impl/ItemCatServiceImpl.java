package com.cenmo.mogu.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.mapper.ItemCatMapper;
import com.cenmo.mogu.pojo.ItemCat;
import com.cenmo.mogu.rest.dao.JedisClient;
import com.cenmo.mogu.rest.pojo.CatNode;
import com.cenmo.mogu.rest.pojo.CatResult;
import com.cenmo.mogu.rest.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${mogu.redis.item.itemCatKey}")
	private String itemCatKey;
	
	@Override
	public CatResult getItemCatList() {
		try {
			String string = jedisClient.hget(itemCatKey, "itemCatList");
			if(!StringUtils.isBlank(string)) {
				return JSONObject.parseObject(string, CatResult.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0).subList(0, 14));
		
		try {
			String jsonString = JSON.toJSONString(catResult);
//			System.out.println(jsonString);
			jedisClient.hset(itemCatKey, "itemCatList", jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catResult;
	}

	private List<?> getCatList(long parentId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("parent_id",parentId);

		List<ItemCat> list = baseMapper.selectByMap(map);
		
		List resList = new ArrayList<>();
		for(ItemCat tbItemCat:list) {
			if(!tbItemCat.getIsParent()) {
				resList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
				continue;
			}
			
			CatNode catNode = new CatNode();
			if(parentId == 0) {
				catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");		
			}
			else {
				catNode.setName(tbItemCat.getName());
			}
			
			catNode.setUrl("/products/"+tbItemCat.getId()+".html");
			catNode.setItem(getCatList(tbItemCat.getId()));
			resList.add(catNode);
		}
		return resList;
	}

	public String getItemCatKey() {
		return itemCatKey;
	}

	public void setItemCatKey(String itemCatKey) {
		this.itemCatKey = itemCatKey;
	}
}
