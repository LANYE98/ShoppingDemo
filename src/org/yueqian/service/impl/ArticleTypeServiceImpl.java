package org.yueqian.service.impl;

import java.util.List;

import org.yueqian.bean.ArticleType;
import org.yueqian.dao.ArticleTypeDao;
import org.yueqian.dao.impl.ArticleTypeDaoImpl;
import org.yueqian.service.ArticleTypeService;

public class ArticleTypeServiceImpl implements ArticleTypeService {
	
	ArticleTypeDao atd = new ArticleTypeDaoImpl();
	
	@Override
	public List<ArticleType> findAllFirstArticleType() {
		// TODO Auto-generated method stub
		return atd.findAllFirstArticleType();
	}

	@Override
	public ArticleType findFirstArticleTypeByCode(String firstArticleTypeCode) {
		// TODO Auto-generated method stub
		return atd.findFirstArticleTypeByCode(firstArticleTypeCode);
	}

	@Override
	public List<ArticleType> findAllSecondArticleType(
			String firstArticleTypeCode) {
		return atd.findAllSecondArticleType(firstArticleTypeCode);
	}

}
