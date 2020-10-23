package com.cenmo.mogu.manager.service.impl;

import com.cenmo.mogu.pojo.OrderItem;
import com.cenmo.mogu.mapper.OrderItemMapper;
import com.cenmo.mogu.manager.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
