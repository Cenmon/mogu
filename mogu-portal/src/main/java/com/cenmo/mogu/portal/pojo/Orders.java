package com.cenmo.mogu.portal.pojo;

import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.pojo.OrderItem;
import com.cenmo.mogu.pojo.OrderShipping;
import lombok.Data;

import java.util.List;

/**
 * 接收网页的订单参数
 * @author lenovo
 *
 */
@Data
public class Orders extends Order {
	
	private List<OrderItem> orderItems;
	private OrderShipping orderShipping;
	
}
