package com.cenmo.mogu.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.mapper.ItemMapper;
import com.cenmo.mogu.pojo.Item;
import com.cenmo.mogu.pojo.ItemDesc;
import com.cenmo.mogu.pojo.ItemParam;
import com.cenmo.mogu.rest.dao.JedisClient;
import com.cenmo.mogu.rest.service.ItemDescService;
import com.cenmo.mogu.rest.service.ItemParamService;
import com.cenmo.mogu.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamService itemParamService;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${mogu.redis.item.itemKey}")
	private String itemKey;
	
	@Value("${mogu.redis.expireTime}")
	private Integer expireTime;
	
	@Override
	public MoguResult getItemBaseInfo(long itemId) {

		String baseInfoKey = itemKey + ":" + itemId + ":base" ;
		
		try {
			// 获取缓存中的商品缓存信息
			String json = jedisClient.get(baseInfoKey);
			if(!StringUtils.isBlank(json)) {
				// 转化为TbItem的pojo对象
				Item item = JSONObject.parseObject(json, Item.class);
				return MoguResult.ok("data",item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Item item = baseMapper.selectById(itemId);
		
		// 将查到的信息添加到缓存
		try {
			jedisClient.set(baseInfoKey, JSON.toJSONString(item));
			jedisClient.expire(baseInfoKey, expireTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MoguResult.ok("data",item);
	}

	@Override
	public MoguResult getItemDescInfo(long itemId) {
		
		String itemDescKey = itemKey + ":" + itemId + ":desc";
		
		try {
			String json = jedisClient.get(itemDescKey);
			if(!StringUtils.isBlank(json)) {
				ItemDesc itemDesc = JSON.parseObject(json,ItemDesc.class);
				return MoguResult.ok("data",itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ItemDesc itemDesc = itemDescService.getItemDescByItemId(itemId);

		if( itemDesc == null){
			return MoguResult.error(ResultEnum.SQL_OPERATION_ERROR);
		}

		try {
			jedisClient.set(itemDescKey, JSON.toJSONString(itemDesc));
			jedisClient.expire(itemDescKey, expireTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MoguResult.ok("data",itemDesc);
	}

	@Override
	public MoguResult getItemParamInfo(long itemId) {
		String itemParamKey = itemKey + ":" + itemId + ":param";
		
		try {
			String json = jedisClient.get(itemParamKey);
			if(!StringUtils.isBlank(json)) {
				ItemParam itemParam = JSON.parseObject(json,ItemParam.class);
				return MoguResult.ok("data",itemParam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		MoguResult itemParam = itemParamService.getItemParamByItemId(itemId);

		if(itemParam == null){
			return MoguResult.build(false,400, "无该商品规格参数",null);
		}

		try {
			jedisClient.set(itemParamKey, JSON.toJSONString(itemParam));
			jedisClient.expire(itemParamKey, expireTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MoguResult.ok("data",itemParam);
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public Integer getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
	}
}
