package com.cenmo.mogu.sso.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.utils.CookieUtils;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.mapper.UserMapper;
import com.cenmo.mogu.pojo.User;
import com.cenmo.mogu.sso.dao.JedisClient;
import com.cenmo.mogu.sso.service.UserService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Data
@ConfigurationProperties("mogu.sso.redis.user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	@Autowired
	private JedisClient jedisClient;
	
	private String SessionKey;
	
	private Integer ExpireTime;
	
	/**
	 * 根据信息类别检测登录信息是否可用
	 * 1：username
	 * 2：phone
	 * 3：email
	 */
	@Override
	public MoguResult checkLoginData(String param, Integer type) {
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		// 根据check数据
		if(type == 1) {
			wrapper.eq("username",param);
		}else if(type == 2) {
			wrapper.eq("phone",param);
		}else if (type == 3) {
			wrapper.eq("email",param);
		}

		try {
			User user = baseMapper.selectOne(wrapper);

			if( user != null){
				return MoguResult.ok();
			}
			return MoguResult.error();
		} catch (Exception e){
			e.printStackTrace();
			return MoguResult.error(ResultEnum.PARAM_ERROR);
		}
		

	}

	/**
	 * 用户注册
	 */
	@Override
	public MoguResult register(User user) {
		// 利用spring自带的加密工具加密，DigestUtils
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		baseMapper.insert(user);
		return MoguResult.ok();
	}

	/**
	 * 用户登录,同时实现cookie共享
	 */
	@Override
	public MoguResult login(String username, String password,
			HttpServletRequest request,HttpServletResponse response) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("username",username);
		List<User> list = baseMapper.selectByMap(map);
		
		//查找用户名
		if(list==null || list.size()==0) {
			return MoguResult.build(false,400, "用户名或密码错误",null);
		}
		
		User user = list.get(0);

//		System.out.println(user);
		//匹配密码：md5无法逆转，可用加密后的字符串进行比较
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return MoguResult.build(false,400, "用户名或密码错误",null);
		}
		//生成token：redis缓存中用于索引用户信息的key
		String token=UUID.randomUUID().toString();
		String jedisKey=SessionKey+":"+token;
		
		//将token存入redis并设置过期时间
		//存入之前，将user对象的密码清空，防止信息泄露
		user.setPassword(null);
		jedisClient.set(jedisKey, JSONObject.toJSONString(user));
		jedisClient.expire(jedisKey, ExpireTime);
		
		//将token写入cookie（cookie共享，使得其他系统能够登录一次即可访问）
		CookieUtils.setCookie(request, response, "MG_TOKEN", token);
		//返回索引token
		return MoguResult.ok("data",token);
	}

	/**
	 * 根据所给token获取用户信息
	 */
	@Override
	public MoguResult getUserByToken(String token) {
		
		String jedisKey=SessionKey+":"+token;
		String json = jedisClient.get(jedisKey);
//		System.out.println(json);
		if(StringUtils.isBlank(json)) {
			return MoguResult.build(false,400, "当前session的token已过期",null);
		}
		
		//重新设置过期时间
		jedisClient.expire(jedisKey, ExpireTime);
		
		return MoguResult.ok("",JSONObject.parseObject(json,User.class));
	}

	/**
	 * 根据token删除redis中的用户session信息
	 */
	@Override
	public MoguResult deleteUserByToken(String token,
			HttpServletRequest request,HttpServletResponse response) {
		String jedisKey = SessionKey + ":" +token;
		jedisClient.del(jedisKey);
//		CookieUtils.deleteCookie(request, response, "MG_TOKEN");
		return MoguResult.ok();
	}

}
