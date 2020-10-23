package com.cenmo.mogu.search.mapper;


import com.cenmo.mogu.search.pojo.SolrItem;

import java.util.List;

public interface SolrItemMapper {
	List<SolrItem> getItemList();
	
	SolrItem getItemById(long id);
}
