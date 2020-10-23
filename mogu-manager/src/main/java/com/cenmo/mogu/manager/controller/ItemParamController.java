package com.cenmo.mogu.manager.controller;


import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemParam;
import com.cenmo.mogu.manager.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品规则参数 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/item-param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @GetMapping("/show/{itemId}")
    public String showItemParamHTML(@PathVariable long itemId, Model model) {
        String string = itemParamService.getItemParamHTMLByItemId(itemId);
        model.addAttribute("itemParam", string);
        return "item-param-show";
    }

    @GetMapping("/query/{id}")
    public ItemParam getItemParamById(@PathVariable long id) {
        return itemParamService.getItemParamById(id);
    }

    @GetMapping("/query/cat/{itemCatId}")
    public MoguResult getItemParamByCid(@PathVariable long itemCatId) {
        // 根据商品类别cid查询规格参数
        return itemParamService.getItemParamByCid(itemCatId);
    }
}

