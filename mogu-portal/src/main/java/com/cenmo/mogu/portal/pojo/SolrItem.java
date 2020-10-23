package com.cenmo.mogu.portal.pojo;

import lombok.Data;

@Data
public class SolrItem {
	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String category_name;
	private String item_desc;
	public String[] getImages() {
		if(image != null) {		
			return image.split(",");
		}
		return null;
	}
}
