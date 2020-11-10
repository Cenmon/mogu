package com.cenmo.mogu.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.business.service.OrderService;
import com.cenmo.mogu.mapper.OrderMapper;
import com.cenmo.mogu.pojo.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public List<Order> getOrderList() {
        return baseMapper.selectList(null);
    }

    @Override
    public Order getOrderById(String id) {

        return baseMapper.selectById(id);
    }
}
