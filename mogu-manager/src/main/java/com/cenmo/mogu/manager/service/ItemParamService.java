package com.cenmo.mogu.manager.service;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品规则参数 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface ItemParamService extends IService<ItemParam> {

    // 通过商品id获取规格参数的html代码
    String getItemParamHTMLByItemId(long itemId);

    MoguResult getItemParamByCid(long cid);

    ItemParam getItemParamById(long id);

    MoguResult insertItemParam(ItemParam itemParam);
}
