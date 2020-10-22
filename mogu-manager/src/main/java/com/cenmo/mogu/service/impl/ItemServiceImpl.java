package com.cenmo.mogu.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cenmo.mogu.common.utils.IDUtils;
import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Item;
import com.cenmo.mogu.mapper.ItemMapper;
import com.cenmo.mogu.pojo.ItemDesc;
import com.cenmo.mogu.pojo.ItemParam;
import com.cenmo.mogu.service.ItemCatService;
import com.cenmo.mogu.service.ItemDescService;
import com.cenmo.mogu.service.ItemParamService;
import com.cenmo.mogu.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    public ItemDescService itemDescService;

    @Autowired
    public ItemParamService itemParamService;

    private final long INSTOCK_ITEM_CODE = 1; // 上架架商品状态码
    private final long RESHELF_ITEM_CODE = 2; // 下架商品状态码

    @Override
    public Item getItemById(long itemId) {
        // 根据商品id查询
        Item item = baseMapper.selectById(itemId);
        return item;
    }

    // 商品列表查询
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //分页处理
        Page<Item> itemPage = new Page<>(page,rows);
        //查询分页记录
        IPage<Item> iPage = baseMapper.selectPage(itemPage, null);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();

        result.setTotal(iPage.getTotal());
        result.setRows(iPage.getRecords());
        return result;
    }

    @Override
    public MoguResult AddItem(Item item, ItemDesc itemDesc, String itemParam) throws Exception {
        // 生成商品id
        Long itemId = IDUtils.genItemId();

        // 补全商品pojo
        item.setId(itemId);
        item.setStatus( 1 );
//        item.setCreated(new Date());
//        item.setUpdated(new Date());
        baseMapper.insert(item);

        if(AddItemDesc(itemId, itemDesc).getStatus() != 200) {
            throw new RuntimeException();
        }

        if(AddItemParam(itemId, itemParam).getStatus() != 200) {
            throw new RuntimeException();
        }

        return MoguResult.ok();
    }

    @Override
    public MoguResult AddItemDesc(long itemId, ItemDesc itemDesc) {
        // 补全商品描述pojo
        itemDesc.setItemId(itemId);
//        itemDesc.setCreated(new Date());
//        itemDesc.setUpdated(new Date());

        itemDescService.insertItemDesc(itemDesc);
        return MoguResult.ok();
    }

    @Override
    public MoguResult AddItemParam(long itemId, String itemParamData) {
        // 新建商品参数pojo类
        ItemParam itemParam = new ItemParam();
//        itemParamItem.setCreated(new Date());
//        itemParamItem.setUpdated(new Date());
        itemParam.setItemId(itemId);
        itemParam.setParamData(itemParamData);
        // 插入商品参数
        itemParamService.insertItemParam(itemParam);
        return MoguResult.ok();
    }

    @Override
    public MoguResult DeleteItem(long[] ids) {
        int i = baseMapper.deleteBatchIds(Arrays.asList(ids));

        if( i == ids.length){
            return MoguResult.ok();
        }
        else{
            return MoguResult.error();
        }
    }

    @Override
    public MoguResult InstockItem(long[] ids) {
        Item item = new Item();
        UpdateWrapper<Item> wrapper = new UpdateWrapper<>();
        wrapper.eq("status",INSTOCK_ITEM_CODE);

        for (long id : ids) {
            item.setId(id);
            baseMapper.update(item,wrapper);
        }
        return MoguResult.ok();
    }

    @Override
    public MoguResult ReshelfItem(long[] ids) {
        Item item = new Item();
        UpdateWrapper<Item> wrapper = new UpdateWrapper<>();
        wrapper.eq("status",RESHELF_ITEM_CODE);

        for (long id : ids) {
            item.setId(id);
            baseMapper.update(item,wrapper);
        }
        return MoguResult.ok();
    }

    @Override
    public MoguResult UpdateItem(Item item) {
        baseMapper.updateById(item);
        return MoguResult.ok();
    }
}
