package com.cenmo.mogu.business.controller;


import com.cenmo.mogu.business.service.OrderService;
import com.cenmo.mogu.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders2")
    public ModelAndView showOrders2(){
        ModelAndView view = new ModelAndView("orders.html");

        List<Order> orders = orderService.getOrderList();

        view.addObject("orders",orders);
        view.addObject("name","cenmo");

        return view;
    }

    @GetMapping("/orders")
    public ModelAndView showOrderListPage(){
        ModelAndView view = new ModelAndView("orders.html");

        List<Order> orders = orderService.getOrderList();

        view.addObject("orders",orders);
        view.addObject("name","cenmo");

        return view;
    }

//    ArrayList<Order> orders = new ArrayList<>();
//    Order order = new Order();
//        order.setOrderId("1001");
//        order.setBuyerNick("Cenmo");
//        order.setPayment("100");
//        order.setStatus(1);
//        order.setCreateTime(new Date());
//        order.setUpdateTime(new Date());
//        orders.add(order);
//
//    Order order2 = new Order();
//        order2.setOrderId("1002");
//        order2.setBuyerNick("yao");
//        order2.setPayment("200");
//        order2.setStatus(2);
//        order2.setCreateTime(new Date());
//        order2.setUpdateTime(new Date());
//        orders.add(order2);
//
//    Order order3 = new Order();
//        order3.setOrderId("1003");
//        order3.setBuyerNick("feng");
//        order3.setPayment("100");
//        order3.setStatus(3);
//        order3.setCreateTime(new Date());
//        order3.setUpdateTime(new Date());
//        orders.add(order3);
//
//    Order order4 = new Order();
//        order4.setOrderId("1004");
//        order4.setBuyerNick("tang");
//        order4.setPayment("100");
//        order4.setStatus(4);
//        order4.setCreateTime(new Date());
//        order4.setUpdateTime(new Date());
//        orders.add(order4);
//
//    Order order5 = new Order();
//        order5.setOrderId("1005");
//        order5.setBuyerNick("luo");
//        order5.setPayment("100");
//        order5.setStatus(5);
//        order5.setCreateTime(new Date());
//        order5.setUpdateTime(new Date());
//        orders.add(order5);
//
//    Order order6 = new Order();
//        order6.setOrderId("1006");
//        order6.setBuyerNick("yin");
//        order6.setPayment("100");
//        order6.setStatus(6);
//        order6.setCreateTime(new Date());
//        order6.setUpdateTime(new Date());
//        orders.add(order6);

}

