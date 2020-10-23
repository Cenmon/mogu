package com.cenmo.mogu.rest.controller;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/info/{itemId}")
	public MoguResult getItemBaseInfo(@PathVariable long itemId) {
		return itemService.getItemBaseInfo(itemId);
	}
	
	@GetMapping("/desc/{itemId}")
	public MoguResult getItemDescInfo(@PathVariable long itemId) {
		return itemService.getItemDescInfo(itemId);
	}
	
	@GetMapping("/param/{itemId}")
	public MoguResult getItemParamInfo(@PathVariable long itemId) {
		return itemService.getItemParamInfo(itemId);
	}
	
}
