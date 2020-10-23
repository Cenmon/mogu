package com.cenmo.mogu.rest.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class CatNode {
	@JSONField(name="n")
	private String name;
	@JSONField(name="u")
	private String url;
	@JSONField(name="i")

	private List<?> item;
}
