package com.mypackage.ekart.dbservice.service;

import com.mypackage.ekart.dbservice.dto.OrderDTO;
import com.mypackage.ekart.dbservice.model.Order;

public interface OrderService {
	OrderDTO submitOrder(Order order);

}
