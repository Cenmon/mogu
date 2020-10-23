package com.cenmo.mogu.portal.service;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.portal.pojo.ShoppingCartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ShoppingCartService {
	MoguResult addShoppingCartItem(String itemId, Integer count, HttpServletRequest request, HttpServletResponse response);
	
	List<ShoppingCartItem> showShoppingCartItem(HttpServletRequest request, HttpServletResponse response);
	
	MoguResult updateItemNum(String itemId,int count,HttpServletRequest request,HttpServletResponse response);
	
	MoguResult deleteShoppingCartItem(String itemId,HttpServletRequest request,HttpServletResponse response);
	
	MoguResult clearShoppingCart(HttpServletRequest request,HttpServletResponse response);
}
