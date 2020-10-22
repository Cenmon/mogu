package com.cenmo.mogu.service;

import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemParam;
import com.cenmo.mogu.pojo.ItemParamModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品规则参数 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
public interface ItemParamModelService extends IService<ItemParamModel> {

    ItemParamModel getItemParamModelById(long id);

    ItemParamModel getItemParamModelByCid(long cid);

    MoguResult insertItemParamModel(ItemParamModel itemParamModel);

    EUDataGridResult getItemParamModelList(int page, int rows);

    MoguResult deleteItemParamModel(long ids);
}
