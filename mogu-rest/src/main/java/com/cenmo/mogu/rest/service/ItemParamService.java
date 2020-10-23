package com.cenmo.mogu.rest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemParam;

/**
 * <p>
 * 商品规则参数 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface ItemParamService extends IService<ItemParam> {
    MoguResult getItemParamByItemId(long itemId);
}
