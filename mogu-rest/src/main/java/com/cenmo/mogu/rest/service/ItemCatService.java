package com.cenmo.mogu.rest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.rest.pojo.CatResult;
import com.cenmo.mogu.pojo.ItemCat;

public interface ItemCatService extends IService<ItemCat> {
	CatResult getItemCatList();
}
