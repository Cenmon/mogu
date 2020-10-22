package com.cenmo.mogu.service;

import com.cenmo.mogu.common.vo.EUTreeNode;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.AdvCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 广告分类表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
public interface AdvCategoryService extends IService<AdvCategory> {
    List<EUTreeNode> getAdvCatList(long parentId);

    MoguResult insertAdvCat(long parentId, String name);

    MoguResult deleteAdvCat(long id);

    MoguResult updateAdvCat(long id,String name);
}
