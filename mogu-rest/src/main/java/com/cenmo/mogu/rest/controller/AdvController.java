package com.cenmo.mogu.rest.controller;

import com.cenmo.mogu.pojo.Adv;
import com.cenmo.mogu.rest.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adv")
public class AdvController {
	@Autowired
	private AdvService advService;
	
	@GetMapping("/list/{cid}")
	public List<Adv> getAdvByCid(@PathVariable long cid){
		return advService.getAdvByCid(cid);
	}
}
