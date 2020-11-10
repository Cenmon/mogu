package com.cenmo.mogu.business.controller;


import com.cenmo.mogu.pojo.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @GetMapping("/product_list")
    public ModelAndView showProList(){
        ModelAndView view = new ModelAndView("product_list.html");

        ArrayList<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setTitle("手机");
        item.setPrice((long)2899);
        item.setStatus(2);
        item.setNum(23);
        items.add(item);

        Item item2 = new Item();
        item2.setTitle("电脑");
        item2.setPrice((long)12899);
        item2.setStatus(1);
        item2.setNum(16);
        items.add(item2);

        view.addObject("items",items);
        view.addObject("name","cenmo");

        return view;
    }

    @GetMapping("/product_info")
    public ModelAndView showItemInfo(){
        ModelAndView view = new ModelAndView("product_info.html");
        view.addObject("name","cenmo");

        return view;
    }

}

