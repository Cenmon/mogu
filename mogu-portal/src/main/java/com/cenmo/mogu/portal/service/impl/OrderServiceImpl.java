package com.cenmo.mogu.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cenmo.mogu.common.utils.HttpUtils;
import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.portal.service.OrderService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("mogu.order")
public class OrderServiceImpl implements OrderService {

	private String baseUrl;
	
	private String createUri;
	
	
	/**
	 * 前台创建订单：调用订单系统的服务接口，完成订单创建功能
	 */
	@Override
	public String createOrder(Order order) {
		
		//调用mogu_order的服务接口，提交订单
		try {			
			String json = HttpUtils.httpPost(baseUrl+createUri, JSON.toJSONString(order));
			JSONObject parseObject = JSONObject.parseObject(json);
			if(parseObject.getString("status").equals("200")) {
				return parseObject.getString("data");
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

	public String getCreateUri() {
		return createUri;
	}

	public void setCreateUri(String createUri) {
		this.createUri = createUri;
	}
}
