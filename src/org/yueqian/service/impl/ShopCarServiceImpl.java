package org.yueqian.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.yueqian.bean.ShopCar;
import org.yueqian.dao.ArticleDao;
import org.yueqian.dao.ShopCarDao;
import org.yueqian.dao.impl.ArticleDaoImpl;
import org.yueqian.dao.impl.ShopCarDaoImpl;
import org.yueqian.service.ShopCarService;

import com.moms.mapper.ShopCarMapper;

public class ShopCarServiceImpl implements ShopCarService {

	ShopCarDao shopCarDao=new ShopCarDaoImpl();
	ArticleDao articleDao=new ArticleDaoImpl();
	
	@Autowired
	ShopCarMapper shopCarMapper;
	
	@Override
	public List<ShopCar> showUserShopChar(int userid) {
		List<ShopCar> list=shopCarDao.showUserShopChar(userid);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				list.get(i).setArticle(articleDao.findById(list.get(i).getArticleId().toString()));
			}
		}
		return list;
	}

	@Override
	public int updateCar(Integer id, Integer userid, Integer buyNum) {
		ShopCar shopCar=new ShopCar();
		shopCar.setArticleId(id);
		shopCar.setBuyNum(buyNum);
		shopCar.setUserId(userid);
		return shopCarMapper.updateBuyNumByPrimaryKey(shopCar);
	}

	@Override
	public int deleteCar(Integer id, Integer userid) {
		ShopCar shopCar=new ShopCar();
		shopCar.setArticleId(id);
		shopCar.setUserId(userid);
		return shopCarMapper.deleteByAidUid(shopCar);
	}

	/**
	 * 添加入购物车，先判断是否有同类商品存在
	 * 有：buyNum叠加；无：新增列
	 */
	public int insertCar(Integer Aid,Integer buyNum,Integer userid) {
		ShopCar shopCar=new ShopCar();
		shopCar.setArticleId(Aid);
		shopCar.setBuyNum(buyNum);
		shopCar.setUserId(userid);
		ShopCar sc=shopCarMapper.selectByAidUid(shopCar);
		if(sc!=null){
			shopCar.setBuyNum(sc.getBuyNum()+shopCar.getBuyNum());
			if(shopCarMapper.updateBuyNumByPrimaryKey(shopCar)>0){
				return 1;
			}
		}else{
			shopCarMapper.insert(shopCar);
		}
		return 0;
	}

	@Override
	public int deleteCarById(Integer id) {
		return shopCarMapper.deleteByPrimaryKey(id);
	}

}
