package com.cenmo.mogu.manager.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemParamModel;
import com.cenmo.mogu.mapper.ItemParamModelMapper;
import com.cenmo.mogu.manager.service.ItemParamModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 商品规则参数 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
@Service
public class ItemParamModelServiceImpl extends ServiceImpl<ItemParamModelMapper, ItemParamModel> implements ItemParamModelService {

    @Override
    public ItemParamModel getItemParamModelById(long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public ItemParamModel getItemParamModelByCid(long cid) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("item_cat_id",cid);
        List<ItemParamModel> itemParamModels = baseMapper.selectByMap(map);

        if(itemParamModels != null && itemParamModels.size()>0){
            return itemParamModels.get(0);
        }
        return null;
    }

    @Override
    public MoguResult insertItemParamModel(ItemParamModel itemParamModel) {
        // 补全itemparam类
//        itemParam.setCreated(new Date());
//        itemParam.setUpdated(new Date());

        baseMapper.insert(itemParamModel);
        return MoguResult.ok();
    }

    @Override
    public EUDataGridResult getItemParamModelList(int page, int rows) {
        Page<ItemParamModel> paramPage = new Page<>(page, rows);

        IPage<ItemParamModel> iPage = baseMapper.selectPage(paramPage, null);

        EUDataGridResult result = new EUDataGridResult();
        result.setTotal(iPage.getTotal());
        result.setRows(iPage.getRecords());

        return result;
    }

    @Override
    public MoguResult deleteItemParamModel(long ids) {
        baseMapper.deleteById(ids);
        return MoguResult.ok();
    }
}
