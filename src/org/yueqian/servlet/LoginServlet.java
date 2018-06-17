package org.yueqian.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.yueqian.bean.User;
import org.yueqian.service.UserService;
import org.yueqian.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	UserService userService = new UserServiceImpl();
	
	/**
	 * 跳转登录页面
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginServlet-doGet");
		request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
	}
	
	/**
	 * 登录功能
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginServlet-doPost");
		request.setCharacterEncoding("utf-8");
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
		System.out.println("loginName="+loginName+" password="+password);
		User user = userService.findByLoginName(loginName);
		if(user!=null){
			if(user.getPassword().equals(password)){
				System.out.println(loginName+"用户:密码正确！");
				//将用户信息设置到session中
				HttpSession session=request.getSession(true);
				session.setAttribute("session_user", user);
				request.getRequestDispatcher("IndexServlet").forward(request, response);
			}else{
				System.out.println(loginName+"用户:密码错误！");
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}
		}else{
			System.out.println(loginName+"用户:不存在！");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		}
	}

}
