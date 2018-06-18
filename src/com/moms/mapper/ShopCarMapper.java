package com.moms.mapper;

import org.yueqian.bean.ShopCar;

public interface ShopCarMapper {

	int updateBuyNumByPrimaryKey(ShopCar shopCar);
	int deleteByAidUid(ShopCar shopCar);
	int deleteByPrimaryKey(Integer id);
	int insert(ShopCar shopCar);
	ShopCar selectByAidUid(ShopCar shopCar);
	ShopCar selectById(Integer id);
	
}
