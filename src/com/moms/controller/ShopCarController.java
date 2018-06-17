package com.moms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yueqian.bean.ShopCar;
import org.yueqian.bean.User;
import org.yueqian.service.ShopCarService;

@Controller
public class ShopCarController {
	
	@Autowired
	ShopCarService shopCarService;
	
	@RequestMapping("/updateCar")
	public String updateCar(Integer id, Integer buyNum,
			HttpServletRequest request){
		System.out.println("ShopCarController-updateCar");
		User user=(User)request.getSession().getAttribute("session_user");
		System.out.println("id="+id+" userid="+user.getId()+" buyNum="+buyNum);
		int result=shopCarService.updateCar(id, user.getId(), buyNum);
		System.out.println("result="+result);
		if(result==1){
			List<ShopCar> list=(List<ShopCar>)request.getSession().getAttribute("shopCars");
			for(int i=0;i<list.size();i++){
				if(list.get(i).getArticleId()==id && list.get(i).getUserId()==user.getId()){
					list.get(i).setBuyNum(buyNum);
				}
			}
		}
		return "jsp/shopCar.jsp";
	}
	
	@RequestMapping("/deleteCar")
	public String deleteCar(Integer id,HttpServletRequest request){
		System.out.println("ShopCarController-deleteCar");
		System.out.println("id="+id);
		User user=(User)request.getSession().getAttribute("session_user");
		int result=shopCarService.deleteCar(id, user.getId());
		System.out.println("result="+result);
		if(result==1){
			List<ShopCar> list=(List<ShopCar>)request.getSession().getAttribute("shopCars");
			for(int i=0;i<list.size();i++){
				if(list.get(i).getArticleId()==id && list.get(i).getUserId()==user.getId()){
					list.remove(i);
				}
			}
		}
		return "jsp/shopCar.jsp";
	}
	
	@RequestMapping("/buy")
	public String buy(Integer id,Integer buyNum,
			HttpServletRequest request){
		System.out.println("ShopCarController-buy");
		User user=(User)request.getSession().getAttribute("session_user");
		System.out.println("Aid="+id+" buyNum="+buyNum+" userid="+user.getId());
		shopCarService.insertCar(id, buyNum, user.getId());
		return "ShowShopCarItemServlet";
	}
	
}
