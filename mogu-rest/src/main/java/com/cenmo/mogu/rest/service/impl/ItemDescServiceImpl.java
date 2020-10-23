package com.cenmo.mogu.rest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.mapper.ItemDescMapper;
import com.cenmo.mogu.pojo.ItemDesc;
import com.cenmo.mogu.rest.service.ItemDescService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品描述表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class ItemDescServiceImpl extends ServiceImpl<ItemDescMapper, ItemDesc> implements ItemDescService {

    @Override
    public ItemDesc getItemDescByItemId(long itemId) {
        QueryWrapper<ItemDesc> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id",itemId);

        try {
            ItemDesc itemDesc = baseMapper.selectOne(wrapper);
            return itemDesc;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
