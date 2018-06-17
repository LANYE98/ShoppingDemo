package org.yueqian.service;

import java.util.List;

import org.yueqian.bean.Article;

public interface ArticleService {
	
	public List<Article> findByCodeAndKeyWord(String keyword, String typecode);
	
	public Article findById(String id);
	
}
