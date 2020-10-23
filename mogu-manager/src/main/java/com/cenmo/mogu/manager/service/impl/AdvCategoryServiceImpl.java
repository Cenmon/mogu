package com.cenmo.mogu.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cenmo.mogu.common.vo.EUTreeNode;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.AdvCategory;
import com.cenmo.mogu.mapper.AdvCategoryMapper;
import com.cenmo.mogu.manager.service.AdvCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 广告分类表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
@Service
public class AdvCategoryServiceImpl extends ServiceImpl<AdvCategoryMapper, AdvCategory> implements AdvCategoryService {
    @Override
    public List<EUTreeNode> getAdvCatList(long parentId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("parent_id",parentId);

        //根据查询条件查询
        List<AdvCategory> list = baseMapper.selectByMap(map);
        // 将查询结果转化为EUTreeNode返回客户端
        List<EUTreeNode> resEuTreeNodes = new ArrayList<>();
        for(AdvCategory tbAdvCat:list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbAdvCat.getId());
            node.setText(tbAdvCat.getName());
            node.setState(tbAdvCat.getIsParent()?"closed":"open");
            resEuTreeNodes.add(node);
        }
        //返回结果
        return resEuTreeNodes;
    }

    @Override
    @Transactional
    public MoguResult insertAdvCat(long parentId,String name) {
        AdvCategory advCategory = new AdvCategory();
        advCategory.setName(name);
        advCategory.setIsParent(false);
        advCategory.setStatus(1);//'状态。可选值:1(正常),2(删除)',
        advCategory.setParentId(parentId);
        advCategory.setSortOrder(1);
//        advCategory.setCreated(new Date());
//        advCategory.setUpdated(new Date());

        baseMapper.insert(advCategory);// mybatis主键返回：插入记录后主键自动返回，即advCategory的id属性自动补全

        // 若父节点为叶子节点，则添加节点后为非叶子节点
        AdvCategory parentCat = baseMapper.selectOne(new QueryWrapper<AdvCategory>().eq("id",parentId));
        if(!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            baseMapper.updateById(parentCat);
        }
        //返回结果
        return MoguResult.ok();
    }

    @Override
    @Transactional
    public MoguResult deleteAdvCat(long id) {

        // 删除当前节点
        baseMapper.deleteById(id);

        // 查找父节点下的其他节点个数
        AdvCategory advCategory = baseMapper.selectById(id);
        long parentId = advCategory.getParentId();
        QueryWrapper<AdvCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        if( baseMapper.selectCount(wrapper) ==0 ) { //若为0，则将父节点设为叶子节点
            // 查找父节点
            AdvCategory advCat = baseMapper.selectOne(new QueryWrapper<AdvCategory>().eq("id",parentId));
            advCat.setIsParent(false);
            baseMapper.updateById(advCat);
        }
        return MoguResult.ok();
    }

    @Override
    @Transactional
    public MoguResult updateAdvCat(long id, String name) {
        AdvCategory advCat = baseMapper.selectById(id);
        advCat.setName(name);
//        advCat.setUpdated(new Date());
        baseMapper.updateById(advCat);
        return MoguResult.ok();
    }
}
