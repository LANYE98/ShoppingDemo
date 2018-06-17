package org.yueqian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yueqian.bean.Article;
import org.yueqian.bean.ArticleType;
import org.yueqian.service.ArticleService;
import org.yueqian.service.ArticleTypeService;
import org.yueqian.service.impl.ArticleServiceImpl;
import org.yueqian.service.impl.ArticleTypeServiceImpl;

public class ItemServlet extends HttpServlet {
	ArticleService as = new ArticleServiceImpl();
	ArticleTypeService ats = new ArticleTypeServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取所有的一级物品类型
		List<ArticleType>  firstArticleTypes = ats.findAllFirstArticleType();
				
		String id = request.getParameter("id");
		Article article = as.findById(id);
		request.setAttribute("article", article);
		request.setAttribute("firstArticleTypes", firstArticleTypes);
		request.getRequestDispatcher("jsp/item.jsp").forward(request, response);

	}

}
