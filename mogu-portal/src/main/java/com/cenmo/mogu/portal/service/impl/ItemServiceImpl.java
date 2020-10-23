package com.cenmo.mogu.portal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cenmo.mogu.common.utils.HttpUtils;
import com.cenmo.mogu.pojo.ItemDesc;
import com.cenmo.mogu.portal.pojo.ItemInfo;
import com.cenmo.mogu.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties("mogu.rest.item")
public class ItemServiceImpl implements ItemService {

	@Value("${mogu.rest.baseUrl}")
	private String baseUrl;
	
	private String itemInfoUri;
	
	private String itemDescInfoUri;
	
	private String itemParamInfoUri;
	
	@Override
	public ItemInfo getItemBaseInfoById(long itemId) {
		try {
			String json = HttpUtils.httpGet(baseUrl+itemInfoUri+"/"+itemId);
			if(!StringUtils.isBlank(json)) {
				JSONObject jsonObject = JSONObject.parseObject(json);
				if(jsonObject.getString("status").equals("200")) {
					return JSONObject.parseObject(jsonObject.getString("data"),ItemInfo.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getItemDescInfoById(long itemId) {
		
		try {
			String json = HttpUtils.httpGet(baseUrl + itemDescInfoUri +"/" + itemId);
			if(!StringUtils.isBlank(json)) {
				JSONObject jsonObject = JSONObject.parseObject(json);
				if(jsonObject.getString("status").equals("200")) {
					ItemDesc itemDesc = JSONObject.parseObject(jsonObject.getString("data"),ItemDesc.class);
					return itemDesc.getItemDesc();
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getItemParamInfoById(long itemId) {
		
		try {
			String json = HttpUtils.httpGet(baseUrl+itemParamInfoUri+"/"+itemId);
			if(!StringUtils.isBlank(json)) {
				JSONObject jsonObject = JSONObject.parseObject(json);
				if(jsonObject.getString("status").equals("200")) {
					JSONObject paramItemJson = JSONObject.parseObject(jsonObject.getString("data"));
					List<Map> paramData = JSONObject.parseObject(paramItemJson.getString("paramData"),new TypeReference<List<Map>>() {});
					
					StringBuffer sb = new StringBuffer();
					
					// 生成规格参数html代码
					sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
					sb.append("    <tbody>\n");
					for (Map m1 : paramData) {
						
						// 设置主体group
						sb.append("        <tr>\n");
						sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
						sb.append("        </tr>\n");
						
						//设置param（内部为List<map>，即多个map组成的list，另map由“k”，“v”键组成）
//						List<Map> params = JSONObject.parseArray(m1.get("params"),new TypeReference<List<Map>>(){});
						List<Map> params = (List<Map>)m1.get("params");
						for (Map m2 : params) {
							sb.append("        <tr>\n");
							sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
							sb.append("            <td>"+m2.get("v")+"</td>\n");
							sb.append("        </tr>\n");
						}
					}
					sb.append("    </tbody>\n");
					sb.append("</table>");
					//返回html片段
					return sb.toString();
				}
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

	public String getItemInfoUri() {
		return itemInfoUri;
	}

	public void setItemInfoUri(String itemInfoUri) {
		this.itemInfoUri = itemInfoUri;
	}

	public String getItemDescInfoUri() {
		return itemDescInfoUri;
	}

	public void setItemDescInfoUri(String itemDescInfoUri) {
		this.itemDescInfoUri = itemDescInfoUri;
	}

	public String getItemParamInfoUri() {
		return itemParamInfoUri;
	}

	public void setItemParamInfoUri(String itemParamInfoUri) {
		this.itemParamInfoUri = itemParamInfoUri;
	}
}
