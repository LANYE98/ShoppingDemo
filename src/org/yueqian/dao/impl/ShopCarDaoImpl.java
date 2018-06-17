package org.yueqian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.yueqian.bean.Article;
import org.yueqian.bean.ShopCar;
import org.yueqian.dao.BaseDao;
import org.yueqian.dao.ShopCarDao;

public class ShopCarDaoImpl extends BaseDao<ShopCar> implements ShopCarDao {

	@Override
	public List<ShopCar> showUserShopChar(int userid) {
		String sql="select * from shopcar where userId=?";
		List<ShopCar> list=executQuery(sql, userid);
		if(list.size()>0) return list;
		return null;
	}

	@Override
	public ShopCar getEntity(ResultSet rs) {
		ShopCar shopCar=new ShopCar();
		try {
			shopCar.setId(rs.getInt("ID"));
			shopCar.setArticleId(rs.getInt("articleId"));
			shopCar.setBuyNum(rs.getInt("buyNum"));
			shopCar.setUserId(rs.getInt("userId"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shopCar;
	}

}
