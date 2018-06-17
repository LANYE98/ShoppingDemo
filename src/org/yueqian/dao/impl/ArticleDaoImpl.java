package org.yueqian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.yueqian.bean.Article;
import org.yueqian.dao.ArticleDao;
import org.yueqian.dao.BaseDao;

public class ArticleDaoImpl extends BaseDao<Article> implements ArticleDao {

	@Override
	public List<Article> findByCodeAndKeyWord(String keyword, String typecode) {
		List<Article> list = new ArrayList<Article>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from article where type_code like ? ");
		if(keyword!=null && !keyword.equals("")){
			sb.append("and title like ?");
			list = executQuery(sb.toString(), typecode+"%","%"+keyword+"%");
		}else
			list = executQuery(sb.toString(), typecode+"%");
		return list;
	}

	@Override
	public Article findById(String id) {
		String sql = "select * from article where id=?";
		List<Article> list = executQuery(sql, id);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public Article getEntity(ResultSet rs) {
		Article article = new Article();
		try {
			article.setId(rs.getInt("id"));
			article.setTitle(rs.getString("title"));
			article.setSupplier(rs.getString("supplier"));
			article.setPrice(rs.getDouble("price"));
			article.setDiscount(rs.getDouble("discount"));
			article.setLocality(rs.getString("locality"));
			article.setPutawayDate(rs.getDate("putaway_date"));
			article.setStorage(rs.getInt("storage"));
			article.setImage(rs.getString("image"));
			article.setDescription(rs.getString("description"));
			article.setTypeCode(rs.getString("type_code"));
			article.setCreateDate(rs.getDate("create_date"));
			article.setDisabled(rs.getString("disabled"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

}
