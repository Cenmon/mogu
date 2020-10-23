package com.cenmo.mogu.portal.controller;

import com.cenmo.mogu.pojo.Order;
import com.cenmo.mogu.portal.pojo.ShoppingCartItem;
import com.cenmo.mogu.portal.service.OrderService;
import com.cenmo.mogu.portal.service.impl.ShoppingCartServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private ShoppingCartServiceImpl shoppingCartService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 展示订单结算页面:显示已购购物车，结算金额等等
	 * @return
	 */
	@GetMapping("/order-cart")
	public ModelAndView showOrderCount(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView("order-cart");
		List<ShoppingCartItem> list = shoppingCartService.getShoppingCartList(request);
		view.addObject("cartList", list);
		return view;
	}
	
	/**
	 * 前台创建订单
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create",method = RequestMethod.POST)
	public ModelAndView createOrder(Order order,
							  HttpServletRequest request, HttpServletResponse response) {
		try {
			//补全order对象
//			TbUser user = (TbUser)request.getSession().getAttribute("user");
//			order.setUserId(user.getId());
//			order.setBuyerNick(user.getUsername());
			ModelAndView view = new ModelAndView("/page/success.html");
			//创建订单
			String orderId = orderService.createOrder(order);
			view.addObject("orderId", orderId);
			view.addObject("payment", order.getPayment());
			view.addObject("date",new DateTime().plusDays(3).toString("yyyy-MM-dd") );
			//清空购物车
			shoppingCartService.clearShoppingCart(request, response);
			return view;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView view = new ModelAndView("/error/error.html");
			view.addObject("message", "订单创建失败！");
			return view;
		}
	}
}
