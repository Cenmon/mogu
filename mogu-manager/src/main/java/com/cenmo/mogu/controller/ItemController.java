package com.cenmo.mogu.controller;


import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Item;
import com.cenmo.mogu.pojo.ItemDesc;
import com.cenmo.mogu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 浏览器url带有该格式，则返回相应的itemId的item
    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        Item tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @GetMapping("/list")
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        // 传入的url会自带参数
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping("/save")
    public MoguResult addItem(Item item,String desc,String itemParams) throws Exception {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        MoguResult result = itemService.AddItem(item, itemDesc,itemParams);
        // 更新solr索引库
//		System.out.println(SEARCH_BASE_URL+SEARCH_IMPORT_ITEM_URL);
//		HttpClientUtil.doGet(SEARCH_BASE_URL+"/"+SEARCH_IMPORT_ITEM_URL+"/"+item.getId());
        return result;
    }

    @RequestMapping("/delete")
    public MoguResult deleteItem(long[] ids) {
        try {

            // 更新solr索引库
//			HttpClientUtil.doGet(SEARCH_BASE_URL+SEARCH_DELETE_ITEM_URL+"/"+id);
            return itemService.DeleteItem(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return MoguResult.build(false,500, "删除商品失败",null);
        }
    }

    @RequestMapping("/instock")
    public MoguResult instockItem(long[] ids) {
        try {
            return itemService.InstockItem(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return MoguResult.build(false,500, "上架商品失败",null);
        }
    }

    @RequestMapping("/reshelf")
    public MoguResult reshelfItem(long[] ids) {
        try {
            return itemService.ReshelfItem(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return MoguResult.build(false,500, "下架商品失败",null);
        }
    }

    @RequestMapping("/update")
    public MoguResult updateItem(Item item,String desc,String itemParams) throws Exception{
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        MoguResult result = itemService.AddItem(item, itemDesc,itemParams);
        // 更新solr索引库
//		HttpClientUtil.doGet(SEARCH_BASE_URL+SEARCH_IMPORT_ITEM_URL+"/"+item.getId());
        return result;
    }
}

