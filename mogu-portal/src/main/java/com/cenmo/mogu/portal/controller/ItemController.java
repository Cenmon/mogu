package com.cenmo.mogu.portal.controller;

import com.cenmo.mogu.portal.pojo.ItemInfo;
import com.cenmo.mogu.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/{itemId}")
	public ModelAndView getItemBaseInfoById(@PathVariable long itemId) {
		ModelAndView view = new ModelAndView("item");
		ItemInfo itemInfo = itemService.getItemBaseInfoById(itemId);
		view.addObject("item", itemInfo);
		return view;
	}
	
	@GetMapping(value="/desc/{itemId}",produces="text/html;charset=utf-8")
	public String getItemDescInfoById(@PathVariable long itemId) {
		// 汉字乱码问题：produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8"
		return itemService.getItemDescInfoById(itemId);
	}
	
	@GetMapping(value="/param/{itemId}",produces="text/html;charset=utf-8")
	public String getItemParamInfoById(@PathVariable long itemId) {
		return itemService.getItemParamInfoById(itemId);
	}
}
