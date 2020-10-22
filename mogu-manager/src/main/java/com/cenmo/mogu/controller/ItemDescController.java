package com.cenmo.mogu.controller;


import com.cenmo.mogu.pojo.ItemDesc;
import com.cenmo.mogu.service.ItemCatService;
import com.cenmo.mogu.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品描述表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/item-desc")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    @GetMapping("/{id}")
    public ItemDesc getItemDescById(@PathVariable long id) {
        return itemDescService.getItemDescById(id);
    }
}

