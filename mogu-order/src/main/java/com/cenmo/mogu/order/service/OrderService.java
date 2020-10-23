package com.cenmo.mogu.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.pojo.OrderItem;
import com.cenmo.mogu.pojo.OrderShipping;

import java.util.List;

public interface OrderService extends IService<Order> {
	
	MoguResult createOrder(Order order, List<OrderItem> orderDetailList, OrderShipping orderShipping);
	
	MoguResult getOrdersById(String orderId);
	
	MoguResult getOrderByUserName(String username,Integer page,Integer rows);
	
	MoguResult updateOrderStatus(Order order);
}
