package com.cenmo.mogu.portal.service;

import com.cenmo.mogu.portal.pojo.ItemInfo;

public interface ItemService {
	ItemInfo getItemBaseInfoById(long itemId);
	
	String getItemDescInfoById(long itemId);
	
	String getItemParamInfoById(long itemId);
}
