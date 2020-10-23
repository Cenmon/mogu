package com.cenmo.mogu.search.service;


import com.cenmo.mogu.common.vo.MoguResult;

public interface SolrItemService {
	MoguResult importAllItem() throws Exception;
	
	MoguResult importItem(long id) throws Exception;
	
	MoguResult deleteItem(long id) throws Exception;
}
