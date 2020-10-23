package com.cenmo.mogu.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.mapper.OrderItemMapper;
import com.cenmo.mogu.pojo.OrderItem;
import com.cenmo.mogu.order.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

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


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OrderItem> getOrderItemByItemId(String itemId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("item_id",itemId);

        try {
            List<OrderItem> orderItems = baseMapper.selectByMap(map);
            return orderItems;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OrderItem> getOrderItemByOrderId(String OrderId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("order_id",OrderId);

        try {
            List<OrderItem> orderItems = baseMapper.selectByMap(map);
            return orderItems;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public MoguResult insertOrderItem(OrderItem orderItem) {
        int insert = baseMapper.insert(orderItem);

        if( insert == 1){
            return MoguResult.ok();
        }
        return MoguResult.error(ResultEnum.SQL_OPERATION_ERROR);
    }
}
