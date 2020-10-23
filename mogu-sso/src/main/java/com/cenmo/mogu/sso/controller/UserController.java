package com.cenmo.mogu.sso.controller;

import com.alibaba.fastjson.JSONObject;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.utils.ExceptionUtil;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.User;
import com.cenmo.mogu.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/check/{param}/{type}")
	public Object CheckLoginData(@PathVariable String param,@PathVariable Integer type,String callback) {
		// url中的中文转码
		try {
			param = new String(param.getBytes("ISO8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// 若param或type为空，则无法映射到该方法
		MoguResult result = null;
		if(type < 1 || type > 3) {
			result = MoguResult.error(ResultEnum.VERIFIED_ERROR);
		}else {
			try {
				result = userService.checkLoginData(param, type);				
			} catch (Exception e) {
				e.printStackTrace();
				result = MoguResult.build(false,500, ExceptionUtil.getMessage(e),null);
			}
		}
		
//		String jsonString = JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
//		System.out.println(jsonString);
		if(StringUtils.isBlank(callback)) {
			return result;
		}else {
//			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
//			mappingJacksonValue.setJsonpFunction(callback);
//			return mappingJacksonValue;
			String json = JSONObject.toJSONString(result.getData());
			return callback + "(" + json + ")";
		}
	}

	@PostMapping(value="/register")
	public MoguResult registerUser(User user) {
		try {
			return userService.register(user);
		} catch (Exception e) {
			e.printStackTrace();
			return MoguResult.error(ResultEnum.VERIFIED_ERROR);
		}
	}
	
	@PostMapping(value="/login")
	public MoguResult loginUser(String username,String password,
			HttpServletRequest request,HttpServletResponse response) {
		try {
			//post请求，封存在表单中的内容，影响不大
//			username = new String(username.getBytes("ISO8859-1"),"utf-8");
//			System.out.println(username);
//			System.out.println("username"+username);
//			System.out.println("password"+password);
			return userService.login(username, password,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			return MoguResult.build(false,500, ExceptionUtil.getMessage(e),null);
		}
	}
	
	@GetMapping(value="/token/{token}")
	public Object getUserByToken(@PathVariable String token,String callback) {
		try {
			MoguResult result = userService.getUserByToken(token);
			if(StringUtils.isBlank(callback)) {
				return result;
			}else {
//				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
//				mappingJacksonValue.setJsonpFunction(callback);
//				return mappingJacksonValue;
				String json = JSONObject.toJSONString(result.getData());
				return callback + "(" + json + ")";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MoguResult.build(false,500, ExceptionUtil.getMessage(e),null);
		}
	}
	
	@GetMapping(value="/logout/{token}")
	public Object logout(@PathVariable String token,String callback,
			HttpServletRequest request,HttpServletResponse response) {
		try {
			MoguResult result = userService.deleteUserByToken(token,request,response);
			if(StringUtils.isBlank(callback)) {
				return result;
			}else {
//				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
//				mappingJacksonValue.setJsonpFunction(callback);
//				return mappingJacksonValue;
				String json = JSONObject.toJSONString(result.getData());
				return callback + "(" + json + ")";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MoguResult.build(false,500, ExceptionUtil.getMessage(e),null);
		}
	}
}
