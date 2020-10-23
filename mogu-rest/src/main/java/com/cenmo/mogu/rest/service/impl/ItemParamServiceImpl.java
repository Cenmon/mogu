package com.cenmo.mogu.rest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.mapper.ItemParamMapper;
import com.cenmo.mogu.pojo.ItemParam;
import com.cenmo.mogu.rest.service.ItemParamService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规则参数 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class ItemParamServiceImpl extends ServiceImpl<ItemParamMapper, ItemParam> implements ItemParamService {
    @Override
    public MoguResult getItemParamByItemId(long itemId) {
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id",itemId);

        try {
            ItemParam itemParam = baseMapper.selectOne(wrapper);

            if(itemParam == null){
                return MoguResult.error(ResultEnum.PARAM_ERROR);
            }
            return MoguResult.ok("data",itemParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return MoguResult.error();
    }

}
