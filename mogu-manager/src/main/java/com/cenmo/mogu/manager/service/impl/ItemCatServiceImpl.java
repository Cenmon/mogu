package com.cenmo.mogu.manager.service.impl;

import com.cenmo.mogu.common.vo.EUTreeNode;
import com.cenmo.mogu.pojo.ItemCat;
import com.cenmo.mogu.mapper.ItemCatMapper;
import com.cenmo.mogu.manager.service.ItemCatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {

    @Override
    public List<EUTreeNode> getItemCatList(long parentID) {
        // 创建查询条件
        HashMap<String, Object> map = new HashMap<>();
        map.put("parent_id",parentID);

        //根据查询条件查询
        List<ItemCat> list = baseMapper.selectByMap(map);
        // 将查询结果转化为EUTreeNode返回客户端
        List<EUTreeNode> resEuTreeNodes = new ArrayList<>();
        for(ItemCat tbItemCat:list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
//			System.out.println(tbItemCat.getParentId());
            resEuTreeNodes.add(node);
        }
        //返回结果
        return resEuTreeNodes;
    }

    @Override
    public ItemCat getItemCatById(long id) {

        return baseMapper.selectById(id);
    }
}
