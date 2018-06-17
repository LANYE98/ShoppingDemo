package org.yueqian.dao;

import java.util.List;

import org.yueqian.bean.ArticleType;

public interface ArticleTypeDao {
	
	public List<ArticleType> findAllFirstArticleType();

	public ArticleType findFirstArticleTypeByCode(String firstArticleTypeCode);

	public List<ArticleType> findAllSecondArticleType(String firstArticleTypeCode);
}
