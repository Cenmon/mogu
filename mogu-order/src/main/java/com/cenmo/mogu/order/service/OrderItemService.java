package com.cenmo.mogu.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.OrderItem;

import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface OrderItemService extends IService<OrderItem> {

    List<OrderItem> getOrderItemByItemId(String itemId);

    List<OrderItem> getOrderItemByOrderId(String OrderId);

    MoguResult insertOrderItem(OrderItem orderItem);

}
