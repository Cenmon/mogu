package com.cenmo.mogu.rest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.pojo.Adv;

import java.util.List;

public interface AdvService extends IService<Adv> {
	List<Adv> getAdvByCid(long cid);
}
