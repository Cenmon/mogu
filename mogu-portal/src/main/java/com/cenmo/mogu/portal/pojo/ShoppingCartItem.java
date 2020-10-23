package com.cenmo.mogu.portal.pojo;

import lombok.Data;

@Data
public class ShoppingCartItem {
	private String id;
	private String title;
	private int count;//购物车中该商品的数量
	private long price;
	private String image;
//	public String[] getImages() {
//		if(image!=null) {
//			return image.split(",");
//		}
//		return null;
//	}
}
