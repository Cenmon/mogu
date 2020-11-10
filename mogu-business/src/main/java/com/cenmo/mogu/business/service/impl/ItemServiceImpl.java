package com.cenmo.mogu.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.business.service.ItemService;
import com.cenmo.mogu.mapper.ItemMapper;
import com.cenmo.mogu.pojo.Item;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}
