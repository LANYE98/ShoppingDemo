package org.yueqian.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.yueqian.bean.Article;
import org.yueqian.bean.Order;
import org.yueqian.bean.OrderItem;
import org.yueqian.bean.ShopCar;
import org.yueqian.dao.ArticleDao;
import org.yueqian.dao.impl.ArticleDaoImpl;

import com.moms.mapper.OrderMapper;
import com.moms.mapper.ShopCarMapper;

public class OrderServiceImpl implements org.yueqian.service.OrderService {

	@Autowired
	OrderMapper orderMapper;
	@Autowired
	ShopCarMapper shopCarMapper;
	
	ArticleDao ad = new ArticleDaoImpl();
	
	@Override
	public int saveOrder(Order order) {
		//insert入Order表
		order.setCreateDate(new Date(System.currentTimeMillis()));
		//order.setSendDate(new Date());
		order.setStatus("0");
		int result=orderMapper.insertOrder(order);
		if(result>0){
			ShopCar shopCar=shopCarMapper.selectById(Integer.parseInt(order.getOrderCode()));
			//insert入OrderItem表
			OrderItem orderItem=new OrderItem();
			orderItem.setOrderId(order.getId());
			orderItem.setArticleId(shopCar.getArticleId());
			orderItem.setOrderNum(shopCar.getBuyNum());
			result=orderMapper.insertOrderItem(orderItem);
			return result;
		}
		return 0;
	}

	@Override
	public List<Order> showMyOrders(Integer userid) {
		List<Order> list=orderMapper.selectOrdersByUserId(userid);
		List<OrderItem> listItem;
		for(int i=0;i<list.size();i++){
			listItem=orderMapper.selectOrderItemByOrderId(list.get(i).getId());
			for(int j=0;j<listItem.size();j++){
				Article article=ad.findById(listItem.get(j).getArticleId()+"");
				listItem.get(j).setArticle(article);
			}
			list.get(i).setItems(listItem);
		}
		return list;
	}

}
