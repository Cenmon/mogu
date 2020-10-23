package com.cenmo.mogu.portal.controller;

import com.cenmo.mogu.portal.service.WebAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@Autowired
	private WebAdvService advService;
	
	@GetMapping("/index")
	public String showIndex(Model model) throws Exception {
		String bigAdvString = advService.getContentAdvList();
		String smallAdvString = advService.getContentSmallList();
		model.addAttribute("ad1", bigAdvString);
		model.addAttribute("ad2",smallAdvString);
		return "index";
	}
	
//	@RequestMapping("/{page}")
//	public String showPage(@PathVariable String page){
//		return page;
//	}
}
