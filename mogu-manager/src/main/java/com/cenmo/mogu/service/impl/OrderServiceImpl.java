package com.cenmo.mogu.service.impl;

import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.mapper.OrderMapper;
import com.cenmo.mogu.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
