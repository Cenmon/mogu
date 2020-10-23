package com.cenmo.mogu.portal.controller;

import com.cenmo.mogu.portal.pojo.SearchResult;
import com.cenmo.mogu.portal.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;


@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@GetMapping("/search")
	public ModelAndView search(@RequestParam("q")String queryString,@RequestParam(defaultValue = "1")Integer page) {
//		System.out.println(queryString);
		try {
			if(!StringUtils.isBlank(queryString)) {				
				queryString = new String(queryString.getBytes("ISO8859-1"),"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println("转换后："+queryString);
		SearchResult search = searchService.search(queryString, page);

		ModelAndView view = new ModelAndView("search");
		view.addObject("query", queryString);
		view.addObject("totalPages", search.getPageCount()  );
		view.addObject("itemList", search.getItemList());
		view.addObject("page", page);
		return view;
	}
}
