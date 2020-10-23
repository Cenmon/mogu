package com.cenmo.mogu.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cenmo.mogu.common.utils.HttpUtils;
import com.cenmo.mogu.pojo.Adv;
import com.cenmo.mogu.portal.service.WebAdvService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties("mogu.rest.adv")
public class WebAdvServiceImpl implements WebAdvService {

	@Value("${mogu.rest.baseUrl}")
	private String baseUrl;
	private String indexAdvUri;
	private String smallAdvUri;
	
	@Override
	public String getContentAdvList() throws Exception {
		// 获得首页大广告的内容列表（即cid=89）
		String list = HttpUtils.httpGet(baseUrl+indexAdvUri);
//		System.out.println(list);
		try {
			// 把字符串转化为List<TbContent>
			List<Adv> contentList = JSONObject.parseArray(list, Adv.class);
			
			List<Map> resList = new ArrayList<>(); 
			
			for (Adv tbContent : contentList) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height",240);
				map.put("width",670);
				map.put("srcB",tbContent.getPic2());
				map.put("widthB",550);
				map.put("heightB",240);
				map.put("href",tbContent.getUrl());
				map.put("alt",tbContent.getTitle());
				resList.add(map);
			}
			return JSON.toJSONString(resList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("轮播页初始化失败");
		}
		return null;
	}

	@Override
	public String getContentSmallList() throws Exception {
		// 获取小广告记录（cid=86）
		String json = HttpUtils.httpGet(baseUrl+smallAdvUri);
		
		try {
			List<Adv> list = JSONArray.parseArray(json, Adv.class);
			
			List<Map> resList = new ArrayList<>();
			int index=0;
			for (Adv tbContent : list) {
				Map map = new HashMap<>();
				map.put("alt",tbContent.getTitle());
				map.put("href",tbContent.getUrl());
				map.put("index",index++);
				map.put("src",tbContent.getPic().split(",")[0]);
				map.put("ext","");
				resList.add(map);
			}
			return JSON.toJSONString(resList);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("小广告初始化失败");
		}
		return null;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getIndexAdvUri() {
		return indexAdvUri;
	}

	public void setIndexAdvUri(String indexAdvUri) {
		this.indexAdvUri = indexAdvUri;
	}

	public String getSmallAdvUri() {
		return smallAdvUri;
	}

	public void setSmallAdvUri(String smallAdvUri) {
		this.smallAdvUri = smallAdvUri;
	}
}
