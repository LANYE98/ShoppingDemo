package org.yueqian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.yueqian.bean.ArticleType;
import org.yueqian.dao.ArticleTypeDao;
import org.yueqian.dao.BaseDao;

public class ArticleTypeDaoImpl extends BaseDao<ArticleType> implements ArticleTypeDao {

	@Override
	public List<ArticleType> findAllFirstArticleType() {
		String sql ="select * from article_type where length(code)=4";
		List<ArticleType> list = executQuery(sql);
		return list;
	}

	@Override
	public ArticleType findFirstArticleTypeByCode(String firstArticleTypeCode) {
		String sql = "select * from article_type where code = ?";
		List<ArticleType> list = executQuery(sql,firstArticleTypeCode);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<ArticleType> findAllSecondArticleType(
			String firstArticleTypeCode) {
		String sql = "select * from article_type where length(code)=8 and code like ?";
		List<ArticleType> list = executQuery(sql, firstArticleTypeCode+"%");
		return list;
	}

	@Override
	public ArticleType getEntity(ResultSet rs) {
		ArticleType at = new ArticleType();
		try {
			at.setCode(rs.getString(1));
			at.setName(rs.getString(2));
			at.setRemark(rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return at;
	}

}
