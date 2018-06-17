package org.yueqian.service.impl;

import java.util.List;
import org.yueqian.bean.ShopCar;
import org.yueqian.dao.ArticleDao;
import org.yueqian.dao.ShopCarDao;
import org.yueqian.dao.impl.ArticleDaoImpl;
import org.yueqian.dao.impl.ShopCarDaoImpl;
import org.yueqian.service.ShopCarService;

public class ShopCarServiceImpl implements ShopCarService {

	ShopCarDao shopCarDao=new ShopCarDaoImpl();
	ArticleDao articleDao=new ArticleDaoImpl();
	
	@Override
	public List<ShopCar> showUserShopChar(int userid) {
		List<ShopCar> list=shopCarDao.showUserShopChar(userid);
		for(int i=0;i<list.size();i++){
			list.get(i).setArticle(articleDao.findById(list.get(i).getArticleId().toString()));
		}
		return list;
	}

}
