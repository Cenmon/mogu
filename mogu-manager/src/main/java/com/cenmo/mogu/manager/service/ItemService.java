package com.cenmo.mogu.manager.service;

import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.pojo.ItemDesc;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface ItemService extends IService<Item> {

    Item getItemById(long itemId); // 根据id查询，单表查询，无法分页

    EUDataGridResult getItemList(int page, int rows); // 查询商品，分页查询

    MoguResult AddItem(Item item, ItemDesc tbItemDesc, String itemParam) throws Exception;

    MoguResult AddItemDesc(long itemId,ItemDesc itemDesc);

    MoguResult AddItemParam(long itemId,String itemParam);

    MoguResult DeleteItem(long[] ids);

    MoguResult InstockItem(long[] ids);

    MoguResult ReshelfItem(long[] ids);

    MoguResult UpdateItem(Item item);
}
