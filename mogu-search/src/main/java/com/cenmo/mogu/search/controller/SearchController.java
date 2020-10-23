package com.cenmo.mogu.search.controller;

import com.cenmo.mogu.common.utils.ExceptionUtil;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.search.pojo.SearchResult;
import com.cenmo.mogu.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@GetMapping(value="/query")
	public MoguResult search (@RequestParam("q") String queryString,
							  @RequestParam(defaultValue = "1")Integer page,
							  @RequestParam(defaultValue = "60")Integer rows) {
		
//		System.out.println(queryString);
		if(StringUtils.isBlank(queryString)) {
			return MoguResult.build(false,400, "查询条件不能为空",null);
		}
		SearchResult searchResult = null;
		try {
			// @GetMapping 无需转换编码方式
//			System.out.println("查询条件："+queryString);
//			queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
//			System.out.println("查询条件："+queryString);

			searchResult = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return MoguResult.build(false,500, ExceptionUtil.getMessage(e),null);
		}
		return MoguResult.ok("data",searchResult);
	}
}
