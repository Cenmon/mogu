package com.cenmo.mogu.portal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cenmo.mogu.common.utils.HttpUtils;
import com.cenmo.mogu.pojo.User;
import com.cenmo.mogu.portal.service.LoginService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("mogu.sso")
public class LoginServiceImpl implements LoginService {

	private String baseUrl;
	
	private String usrTokenUri;
	
	private String loginPageUri;
	
	/**
	 * protal系统
	 * 调用sso系统的接口，获取指定token对应的用户信息
	 * @param token
	 * @return
	 */
	@Override
	public User getUserByToken(String token) {
		try {
			String json = HttpUtils.httpGet(baseUrl + usrTokenUri + "/" + token);
			JSONObject taotaoResultObject = JSONObject.parseObject(json);
			if(taotaoResultObject.getString("status").equals("200")) {
				return JSONObject.parseObject(taotaoResultObject.getString("data"),User.class);
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

	public String getUsrTokenUri() {
		return usrTokenUri;
	}

	public void setUsrTokenUri(String usrTokenUri) {
		this.usrTokenUri = usrTokenUri;
	}

	public String getLoginPageUri() {
		return loginPageUri;
	}

	public void setLoginPageUri(String loginPageUri) {
		this.loginPageUri = loginPageUri;
	}
}
