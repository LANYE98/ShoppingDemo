package org.yueqian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yueqian.bean.ShopCar;
import org.yueqian.bean.User;
import org.yueqian.service.ShopCarService;
import org.yueqian.service.impl.ShopCarServiceImpl;

/**
 * 展示购物车信息
 * @author 刘晶卉
 */
public class ShowShopCarItemServlet extends HttpServlet {

	ShopCarService shopCarService=new ShopCarServiceImpl();
	
	/**
	 * 判断用户是否登录，查询用户所有购物车信息
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShowShopCarItemServlet-doGet");
		User user=(User)request.getSession().getAttribute("session_user");
		System.out.println("userid="+user.getId());
		List<ShopCar> list=shopCarService.showUserShopChar(user.getId());
		request.getSession().setAttribute("shopCars", list);
		request.getRequestDispatcher("jsp/shopCar.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShowShopCarItemServlet-doPost");
		doGet(request, response);
	}

}
