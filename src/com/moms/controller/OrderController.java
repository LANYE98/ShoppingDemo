package com.moms.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yueqian.bean.Order;
import org.yueqian.bean.ShopCar;
import org.yueqian.bean.User;
import org.yueqian.service.OrderService;
import org.yueqian.service.ShopCarService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShopCarService shopCarService;
	
	@RequestMapping("/order")
	public ModelAndView order(Integer step,String orderInfo,
			HttpServletRequest request){
		System.out.println("OrderController-order");
		ModelAndView mv=new ModelAndView("jsp/order.jsp");
		System.out.println("step="+step+" orderInfo="+orderInfo);
		mv.addObject("orderInfo", orderInfo);
		List<ShopCar> list=(List<ShopCar>)request.getSession().getAttribute("shopCars");
		String[] IDs=orderInfo.split(",");
		for(int i=0;i<list.size();i++){
			int key=0;
			for(String s:IDs){
				if(list.get(i).getId()==Integer.parseInt(s)){
					key=1;
				}
			}
			if(key==0){
				list.remove(i);
			}
		}
		mv.addObject("orderCars", list);
		return mv;
	}
	
	@RequestMapping("/saveOrder")
	public String saveOrder(String orderInfo, Double countMoney,
			HttpServletRequest request){
		System.out.println("OrderController-saveOrder");
		System.out.println("orderInfo="+orderInfo+" countMoney="+countMoney);
		String[] IDs=orderInfo.split(",");
		User user=(User)request.getSession().getAttribute("session_user");
		Order order;
		for(String s:IDs){
			order=new Order();
			order.setOrderCode(s);
			order.setAmount(countMoney);
			order.setUserId(user.getId());
			orderService.saveOrder(order);
			//在购物车删除相应订单
			shopCarService.deleteCarById(Integer.parseInt(s));
		}
		//跳转查询我的订单
		return "redirect:showOrder.action";
	}
	
	@RequestMapping("/showOrder")
	public ModelAndView showOrder(HttpServletRequest request){
		System.out.println("OrderController-showOrder");
		ModelAndView mv=new ModelAndView("jsp/orderList.jsp");
		User user=(User)request.getSession().getAttribute("session_user");
		List<Order> list=orderService.showMyOrders(user.getId());
		mv.addObject("orders", list);
		return mv;
	}

}
