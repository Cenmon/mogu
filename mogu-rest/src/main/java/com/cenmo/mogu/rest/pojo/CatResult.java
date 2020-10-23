package com.cenmo.mogu.rest.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CatResult {
	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}
