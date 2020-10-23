package com.cenmo.mogu.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.mapper.OrderMapper;
import com.cenmo.mogu.order.dao.JedisClient;
import com.cenmo.mogu.order.pojo.Orders;
import com.cenmo.mogu.order.service.OrderItemService;
import com.cenmo.mogu.order.service.OrderService;
import com.cenmo.mogu.order.service.OrderShippingService;
import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.pojo.OrderItem;
import com.cenmo.mogu.pojo.OrderShipping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


@Service
@ConfigurationProperties("mogu.order")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderShippingService orderShippingService;
	
	private String initOrderId;
	
	private String orderIdIncrKey;
	
	private String detailIdIncrKey;
	
	@Override
	@Transactional
	public MoguResult createOrder(Order order, List<OrderItem> orderDetailList, OrderShipping orderShipping) {
		
		//插入订单表
		//通过redis的incr方法获取不重复的订单编号
			//注：redis为单线程运行，即一次处理一个请求，指定订单编号初始值后，调用incr方法每次获取的值都将不同
		long orderId = jedisClient.incr(orderIdIncrKey);
		order.setOrderId(orderId+"");
		//设置订单状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭'
		order.setStatus(1);
		Date date = new Date();
		order.setUpdateTime(date);
		order.setCreateTime(date);
		//买主是否评价：1：评价 0：未评价
		order.setBuyerRate(0);
		baseMapper.insert(order);
		
		//插入订单详情表
		for (OrderItem tbOrderItem : orderDetailList) {
			//补全订单明细
			long orderDetailId = jedisClient.incr(detailIdIncrKey);
			tbOrderItem.setId(orderDetailId+"");
			tbOrderItem.setOrderId(orderId+"");
			orderItemService.insertOrderItem(tbOrderItem);
		}
		
		//插入物流表
		//补全物流表属性
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingService.insertOrderShipping(orderShipping);
		
		return MoguResult.ok("data",orderId);
	}
	
	@PostConstruct
	private void initRedisOrderIdKey() {
		String orderId = jedisClient.get(orderIdIncrKey);
		if(StringUtils.isBlank(orderId)) {
			jedisClient.set(orderIdIncrKey, initOrderId);
		}
	}

	/**
	 * 通过订单id获取订单信息
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public MoguResult getOrdersById(String orderId) {
		
		Orders orders = new Orders();
		//获取商品信息
		Order order = baseMapper.selectById(orderId);
		order.setOrderId(order.getOrderId());
		order.setPayment(order.getPayment());
		order.setPaymentType(order.getPaymentType());
		order.setStatus(order.getStatus());
		order.setCreateTime(order.getCreateTime());
		order.setPostFee(order.getPostFee());
		order.setOrderId(order.getOrderId());
		order.setBuyerMessage(order.getBuyerMessage());
		order.setBuyerNick(order.getBuyerNick());
		orders.setOrder(order);
		
		//获取订单详情信息

		List<OrderItem> orderItems = orderItemService.getOrderItemByOrderId(orderId);
		orders.setOrderItems(orderItems);
		
		//获取订单物流信息
		OrderShipping OrderShipping = orderShippingService.getOrderShippingByOrderId(orderId);
		orders.setOrderShipping(OrderShipping);
		
		return MoguResult.ok("data",orders);
		
	}

	/**
	 * 根据用户名查询订单信息，并分页返回
	 */
	@Override
	public MoguResult getOrderByUserName(String username, Integer page, Integer rows) {
		Page<Order> orderPage = new Page<>(page,rows);
		QueryWrapper<Order> wrapper = new QueryWrapper<>();
		wrapper.eq("buyer_nick",username);

		IPage<Order> iPage = baseMapper.selectPage(orderPage, wrapper);

		return MoguResult.ok("data",iPage.getRecords());
	}

	@Override
	@Transactional
	public MoguResult updateOrderStatus(Order order) {
//		order.setUpdateTime(new Date());
		baseMapper.updateById(order);
		
		return MoguResult.ok();
	}

	public String getInitOrderId() {
		return initOrderId;
	}

	public void setInitOrderId(String initOrderId) {
		this.initOrderId = initOrderId;
	}

	public String getOrderIdIncrKey() {
		return orderIdIncrKey;
	}

	public void setOrderIdIncrKey(String orderIdIncrKey) {
		this.orderIdIncrKey = orderIdIncrKey;
	}

	public String getDetailIdIncrKey() {
		return detailIdIncrKey;
	}

	public void setDetailIdIncrKey(String detailIdIncrKey) {
		this.detailIdIncrKey = detailIdIncrKey;
	}
}
