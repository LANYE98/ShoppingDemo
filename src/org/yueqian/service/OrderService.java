package org.yueqian.service;

import java.util.List;

import org.yueqian.bean.Order;
import org.yueqian.bean.OrderItem;

public interface OrderService {

	public int saveOrder(Order order);
	public List<Order> showMyOrders(Integer userid);
}
