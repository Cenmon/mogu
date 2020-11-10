package com.cenmo.mogu.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.pojo.Order;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
public interface OrderService extends IService<Order> {

    List<Order> getOrderList();

    Order getOrderById(String id);

}
