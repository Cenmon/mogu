package com.cenmo.mogu.portal.controller;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.portal.pojo.ShoppingCartItem;
import com.cenmo.mogu.portal.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/add/{itemId}")
	public ModelAndView addCartItem(@PathVariable String itemId,
									@RequestParam(defaultValue = "1")Integer num,
									HttpServletRequest request, HttpServletResponse response) {
		shoppingCartService.addShoppingCartItem(itemId, num, request, response);
		ModelAndView view = new ModelAndView("redirect:/page/cart/add/success.html");
//		return "cartSuccess";
		return view;
	}
	
	/**
	 * 购物车添加商品成功重定向的页面
	 * @return
	 */
	@GetMapping("/add/success")
	public ModelAndView addSuccess() {
		return new ModelAndView("cartSuccess");
	}
	
	/**
	 * 修改购物车中商品数目
	 * @param itemId：修改商品的id
	 * @param count：商品数目
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/update/num/{itemId}/{count}")
	@ResponseBody
	public MoguResult updateItemCount(@PathVariable String itemId, @PathVariable int count,
									  HttpServletRequest request, HttpServletResponse response) {
		return shoppingCartService.updateItemNum(itemId, count, request, response);
	}
	
	/**
	 * 删除购物车中指定商品
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/delete/{itemId}")
	public ModelAndView deleteCartItem(@PathVariable String itemId,
			HttpServletRequest request,HttpServletResponse response) {
		shoppingCartService.deleteShoppingCartItem(itemId, request, response);
		return new ModelAndView("redirect:/cart/cart.html");
	}
	
	/**
	 * 展示购物车
	 * 映射到两个url：/cart/cart.html和/cart/show.html
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@GetMapping(value = {"/cart","/show"})
	public ModelAndView showShoppingCartItem(HttpServletRequest request,HttpServletResponse response,Model model) {
		List<ShoppingCartItem> list = shoppingCartService.showShoppingCartItem(request, response);
		ModelAndView view = new ModelAndView("cart");
		view.addObject("cartList", list);
		return view;
	}
}
