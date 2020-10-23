package com.cenmo.mogu.portal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cenmo.mogu.common.utils.HttpUtils;
import com.cenmo.mogu.portal.pojo.SearchResult;
import com.cenmo.mogu.portal.service.SearchService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@ConfigurationProperties("mogu.search")
public class SearchSerciceImpl implements SearchService {
	
	private String baseUrl;
	
	@Override
	public SearchResult search(String queryString, Integer page) {
		Map<String, String> map = new HashMap<>();
		map.put("q", queryString);
		map.put("page",page+"");
		try {
			String json = HttpUtils.httpPost(baseUrl,map.toString());
//			System.out.println(json);
			JSONObject jsonObject = JSONObject.parseObject(json);
			if(jsonObject.getString("status").equals("200")) {
				return JSONObject.parseObject(jsonObject.getString("data"), SearchResult.class);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
