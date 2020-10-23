package com.cenmo.mogu.rest.controller;

import com.alibaba.fastjson.JSON;
import com.cenmo.mogu.rest.pojo.CatResult;
import com.cenmo.mogu.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemcat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@GetMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		
		String json = JSON.toJSONString(catResult);
		String resultString = callback+"("+json+");";
//		System.out.println(resultString);
		return resultString;
	}
	
//	@RequestMapping("/{page}")
//	public String pageshow(@PathVariable String page) {
//		return page;
//	}
}
