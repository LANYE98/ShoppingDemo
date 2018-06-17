package org.yueqian.service;

import java.util.List;

import org.yueqian.bean.ArticleType;

public interface ArticleTypeService {
	
	public List<ArticleType> findAllFirstArticleType();

	public ArticleType findFirstArticleTypeByCode(String firstArticleTypeCode);

	public List<ArticleType> findAllSecondArticleType(String firstArticleTypeCode);
	
}
