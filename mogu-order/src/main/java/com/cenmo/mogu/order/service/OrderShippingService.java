package com.cenmo.mogu.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.OrderShipping;

/**
 * <p>
 * 订单物流表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface OrderShippingService extends IService<OrderShipping> {

    OrderShipping getOrderShippingByOrderId(String OrderId);

    MoguResult insertOrderShipping(OrderShipping orderShipping);
}
