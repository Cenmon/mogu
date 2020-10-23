package com.cenmo.mogu.order.controller;

import com.cenmo.mogu.common.utils.ExceptionUtil;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.order.pojo.Orders;
import com.cenmo.mogu.order.service.OrderService;
import com.cenmo.mogu.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 创建订单
	 * @param
	 * @return
	 */
	@PostMapping(value = "/create")
	public MoguResult createOrder(@RequestBody Orders orders) {
		try {
			return orderService.createOrder(orders.getOrder(), orders.getOrderItems(), orders.getOrderShipping());
		} catch (Exception e) {
			e.printStackTrace();
			return MoguResult.build(false,500, ExceptionUtil.getMessage(e),null);
		}
	}
	
	/**
	 * 通过订单id获取订单信息
	 * @param orderId
	 * @return
	 */
	@GetMapping("/info/{orderId}")
	public MoguResult getOrdersById(@PathVariable String orderId) {
		return orderService.getOrdersById(orderId);
	}
	
	/**
	 * 根据用户名查询订单信息，并分页显示
	 * @param username
	 * @param page
	 * @param count
	 * @return
	 */
	@GetMapping("/list/{username}/{page}/{count}")
	public MoguResult getOrderByUserId(@PathVariable String username,
			@PathVariable Integer page,@PathVariable Integer count) {
		try {
//			System.out.println(username);
//			username = new String(username.getBytes("ISO8859-1"),"utf-8");
//			System.out.println(username);
			return orderService.getOrderByUserName(username, page, count);
		} catch (Exception e) {
			e.printStackTrace();
			return MoguResult.build(false,500, ExceptionUtil.getMessage(e),null);
		}
	}
	
	/**
	 * 修改订单状态
	 * @param order
	 * @return
	 */
	@PostMapping(value="/changeStatus")
	public MoguResult updateOrderStatus(@RequestBody Order order) {
		return orderService.updateOrderStatus(order);
	}
}
