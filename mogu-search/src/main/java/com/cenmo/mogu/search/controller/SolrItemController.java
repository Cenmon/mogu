package com.cenmo.mogu.search.controller;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.search.service.SolrItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/manager")
public class SolrItemController {

	@Autowired
	private SolrItemService solrItemService;
	
	@GetMapping("/importall")
	public MoguResult importAllItems() throws Exception {
		return solrItemService.importAllItem();
	}
	
	@GetMapping("/import/{itemId}")
	public MoguResult importItem(@PathVariable long itemId) throws Exception {
		return solrItemService.importItem(itemId);
	}
	
	@GetMapping("/delete/{itemId}")
	public MoguResult deleteItem(@PathVariable long itemId) throws Exception {
		return solrItemService.deleteItem(itemId);
	}
}
