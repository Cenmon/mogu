package com.cenmo.mogu.rest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Item;

public interface ItemService extends IService<Item> {
	MoguResult getItemBaseInfo(long itemId);
	
	MoguResult getItemDescInfo(long itemId);
	
	MoguResult getItemParamInfo(long itemId);
}
