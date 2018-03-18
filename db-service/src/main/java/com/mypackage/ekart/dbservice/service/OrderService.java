package com.mypackage.ekart.dbservice.service;

import java.util.List;

import com.mypackage.ekart.dbservice.dto.OrderDTO;
import com.mypackage.ekart.dbservice.model.Order;

public interface OrderService {
	OrderDTO submitOrder(Order order);
	OrderDTO getOrderById(Long orderId);
	List<OrderDTO> getAllOrders();
	void deleteOrder(Long orderId);
	void updateOrder(Order order, Long orderId);

}
