package com.cenmo.mogu.manager.service;

import com.cenmo.mogu.common.vo.EUTreeNode;
import com.cenmo.mogu.pojo.ItemCat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface ItemCatService extends IService<ItemCat> {

    List<EUTreeNode> getItemCatList(long parentID);

    ItemCat getItemCatById(long id);

}
