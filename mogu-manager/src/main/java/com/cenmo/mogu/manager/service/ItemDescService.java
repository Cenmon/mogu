package com.cenmo.mogu.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemDesc;

/**
 * <p>
 * 商品描述表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface ItemDescService extends IService<ItemDesc> {
    ItemDesc getItemDescById(long id);

    MoguResult insertItemDesc(ItemDesc itemDesc);
}
