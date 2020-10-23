package com.cenmo.mogu.manager.service.impl;

import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemDesc;
import com.cenmo.mogu.mapper.ItemDescMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.manager.service.ItemDescService;
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
    public ItemDesc getItemDescById(long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public MoguResult insertItemDesc(ItemDesc itemDesc) {
        int insert = baseMapper.insert(itemDesc);
        if ( insert == 1){
            return MoguResult.ok();
        }
        return MoguResult.error(ResultEnum.BAD_SQL_GRAMMAR);
    }

}
