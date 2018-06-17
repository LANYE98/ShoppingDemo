package org.yueqian.service.impl;

import java.util.List;

import org.yueqian.bean.Article;
import org.yueqian.dao.ArticleDao;
import org.yueqian.dao.impl.ArticleDaoImpl;
import org.yueqian.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	
	ArticleDao ad = new ArticleDaoImpl();
	
	@Override
	public List<Article> findByCodeAndKeyWord(String keyword, String typecode) {
		// TODO Auto-generated method stub
		return ad.findByCodeAndKeyWord(keyword, typecode);
	}

	@Override
	public Article findById(String id) {
		// TODO Auto-generated method stub
		return ad.findById(id);
	}
	
}
