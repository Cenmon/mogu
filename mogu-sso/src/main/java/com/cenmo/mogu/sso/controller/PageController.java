package com.cenmo.mogu.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	@GetMapping("/showRegister")
	public String showRegister() {
		return "page/register.html";
	}
	
	@GetMapping("/showLogin")
	public String showLogin(String redirect,Model model) {
		model.addAttribute("redirect", redirect);
		return "page/login.html";
	}
}
