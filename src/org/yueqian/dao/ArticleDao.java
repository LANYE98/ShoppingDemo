package org.yueqian.dao;

import java.util.List;

import org.yueqian.bean.Article;

public interface ArticleDao {

	public List<Article> findByCodeAndKeyWord(String keyword, String typecode);

	public Article findById(String id);
}
