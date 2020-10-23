package com.cenmo.mogu.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemParam;
import com.cenmo.mogu.mapper.ItemParamMapper;
import com.cenmo.mogu.manager.service.ItemParamModelService;
import com.cenmo.mogu.manager.service.ItemParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Autowired
    public ItemParamModelService itemParamModelService;

    @Override
    public String getItemParamHTMLByItemId(long itemId) {
        // 通过商品id查询商品规格参数
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("item_id",itemId);
        ItemParam itemParam = baseMapper.selectOne(wrapper);

        //取规格参数信息
        String paramData = itemParam.getParamData();
        //生成html
        // 把规格参数json数据转换成java对象
        List<Map> maps = JSON.parseObject(paramData, new TypeReference<List<Map>>() {});
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(Map m1:maps) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for(Map m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                sb.append("            <td>"+m2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public MoguResult getItemParamByCid(long cid) {
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",cid);
        ItemParam itemParam = baseMapper.selectOne(wrapper);

        if(itemParam == null){
            return MoguResult.error(ResultEnum.PARAM_ERROR);
        }
        return MoguResult.ok("data",itemParam);
    }

    @Override
    public ItemParam getItemParamById(long id) {
//        ItemParam itemParam = baseMapper.selectById(id);
//
//        if(itemParam == null){
//            return MoguResult.error(ResultEnum.PARAM_ERROR);
//        }
//        return MoguResult.ok("data",itemParam);
        return baseMapper.selectById(id);
    }

    @Override
    @Transactional
    public MoguResult insertItemParam(ItemParam itemParam) {
        int insert = baseMapper.insert(itemParam);

        return MoguResult.ok();
    }
}
