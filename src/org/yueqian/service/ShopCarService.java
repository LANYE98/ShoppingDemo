package org.yueqian.service;

import java.util.List;
import org.yueqian.bean.ShopCar;

public interface ShopCarService {

	public List<ShopCar> showUserShopChar(int userid);
	public int updateCar(Integer id,Integer userid,Integer buyNum);
	public int deleteCar(Integer id,Integer userid);
	public int deleteCarById(Integer id);
	public int insertCar(Integer Aid,Integer buyNum,Integer userid);
	
}
