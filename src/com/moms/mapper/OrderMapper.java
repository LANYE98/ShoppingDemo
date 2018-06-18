package com.moms.mapper;

import java.util.List;

import org.yueqian.bean.Order;
import org.yueqian.bean.OrderItem;

public interface OrderMapper {

	int insertOrder(Order order);
	int insertOrderItem(OrderItem orderItem);
	List<Order> selectOrdersByUserId(Integer userid);
	List<OrderItem> selectOrderItemByOrderId(Integer orderid);
	
}
