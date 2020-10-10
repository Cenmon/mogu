package com.cenmo.mogu.service.impl;

import com.cenmo.mogu.pojo.Item;
import com.cenmo.mogu.mapper.ItemMapper;
import com.cenmo.mogu.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}
