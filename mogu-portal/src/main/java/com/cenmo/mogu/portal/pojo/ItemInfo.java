package com.cenmo.mogu.portal.pojo;

import com.cenmo.mogu.pojo.Item;

/**
 * 用于展示商品基本信息pojo
 * 注：增加了getImages方法
 * @author lenovo
 *
 */
public class ItemInfo extends Item {
	public String[] getImages() {
		String image = getImage();
		if(image != null) {
			return image.split(",");
		}
		return null;
	}
}
