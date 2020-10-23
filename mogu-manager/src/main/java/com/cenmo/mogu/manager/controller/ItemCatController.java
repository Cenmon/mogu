package com.cenmo.mogu.manager.controller;


import com.cenmo.mogu.common.vo.EUTreeNode;
import com.cenmo.mogu.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/item-cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("/list")
    public List<EUTreeNode> getCatChildTreeList(@RequestParam(value = "id",defaultValue = "0")long parentId){
        // 此方法的访问修饰符为public
//		System.out.println(parentId);
        List<EUTreeNode> list = itemCatService.getItemCatList(parentId);
//		for(EUTreeNode node : list) {
//			System.out.println(node.getState());
//		}
        return list;
//		return itemCatService.getItemCatList(parentId);
    }
}

