package com.cenmo.mogu.sso.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService extends IService<User> {
	MoguResult checkLoginData(String param, Integer type);
	
	MoguResult register(User user);
	
	MoguResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
	
	MoguResult getUserByToken(String token);
	
	MoguResult deleteUserByToken(String token,HttpServletRequest request,HttpServletResponse response);
}
