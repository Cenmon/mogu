package com.cenmo.mogu.portal.controller;

import com.cenmo.mogu.pojo.User;
import com.cenmo.mogu.portal.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private LoginService loginService;

	@Value("${mogu.sso.baseUrl}")
	private String baseUrl;

	@Value("${mogu.sso.loginPageUri}")
	private String loginPageUri;

	public boolean preHandle(@CookieValue("MG_TOKEN") String token, HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在handler执行之前的处理
		//判断用户是否登录
		//从cookie取去token
//		String token = CookieUtils.getCookieValue(request, "MG_TOKEN");
		if(StringUtils.isBlank(token)) {
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面
			response.sendRedirect(baseUrl+loginPageUri
			       +"?redirect="+request.getRequestURL());
			//返回false
			return false;
		}
		//根据token获取用户信息，调用sso系统服务
		User user = loginService.getUserByToken(token);
		//取不到用户信息
		if(user == null) {
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面
			response.sendRedirect(baseUrl+loginPageUri
			       +"?redirect="+request.getRequestURL());
			//返回false
			return false;
		}
		//取到用户信息，进行下一步
//		request.getSession().setAttribute("user", user);
		//返回值决定handler是否执行，true：执行，false：不执行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//handler执行之后，返回ModelAndView之前
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//返回ModelAndView之后
		//响应用户之后
	}

}
