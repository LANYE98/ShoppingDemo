package org.yueqian.dao;

import java.util.List;
import org.yueqian.bean.ShopCar;

public interface ShopCarDao {

	public List<ShopCar> showUserShopChar(int userid);
	
}
