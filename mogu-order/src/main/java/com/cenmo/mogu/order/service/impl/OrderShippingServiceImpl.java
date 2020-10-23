package com.cenmo.mogu.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.mapper.OrderShippingMapper;
import com.cenmo.mogu.pojo.OrderShipping;
import com.cenmo.mogu.order.service.OrderShippingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单物流表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class OrderShippingServiceImpl extends ServiceImpl<OrderShippingMapper, OrderShipping> implements OrderShippingService {


    @Override
    public OrderShipping getOrderShippingByOrderId(String OrderId) {
        OrderShipping orderShipping = baseMapper.selectById(OrderId); // orderId为主键
        return orderShipping;
    }

    @Override
    @Transactional
    public MoguResult insertOrderShipping(OrderShipping orderShipping) {
        int insert = baseMapper.insert(orderShipping);

        if( insert == 1){
            return MoguResult.ok();
        }
        return MoguResult.error(ResultEnum.SQL_OPERATION_ERROR);
    }
}
