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

public class IndexServlet extends HttpServlet {

	ArticleTypeService ats = new ArticleTypeServiceImpl();
	ArticleService as = new ArticleServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String typecode = request.getParameter("typecode");
		String keyword = request.getParameter("keyword");

		// 查询出所有的一级分类
		List<ArticleType> firstArticleTypes = ats.findAllFirstArticleType();

		// 判断typecode是否有值 ，如果没有值则是第一次进入，获取第一个类型
		if (typecode == null || typecode.equals("")) {
			typecode = firstArticleTypes.get(0).getCode();
		}
		// 获取请求的一级物品类型，目的是展示在右边窗口左上角（如点击查询的是二级物品类型，则需截取前四位获取一级物品类型）
		String firstArticleTypeCode = typecode.substring(0, 4);
		ArticleType firstArticleType = ats.findFirstArticleTypeByCode(firstArticleTypeCode);

		System.out.println("firstArticleTypeCode===" + firstArticleTypeCode);

		System.out.println("firstArticleType==" + firstArticleType);
		// 获取请求的一级物品类型下所有的二级物品类型
		List<ArticleType> allSecondArticleTypes = ats.findAllSecondArticleType(firstArticleTypeCode);
		System.out
				.println("allSecondArticleTypes=====" + allSecondArticleTypes);

		//根据物品类型和关键字查询所有的商品
		List<Article> articles = as.findByCodeAndKeyWord(keyword, typecode);
		
		request.setAttribute("articles", articles);	
		request.setAttribute("typecode", typecode);
		request.setAttribute("keyword", keyword);
		request.setAttribute("allSecondArticleTypes", allSecondArticleTypes);
		request.setAttribute("firstArticleType", firstArticleType);
		request.setAttribute("firstArticleTypes", firstArticleTypes);
		request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
	
	}

}
