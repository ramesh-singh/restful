package com.mypackage.ekart.dbservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mypackage.ekart.dbservice.dto.OrderDTO;
import com.mypackage.ekart.dbservice.model.LineItem;
import com.mypackage.ekart.dbservice.model.Order;
import com.mypackage.ekart.dbservice.repository.OrderRepository;
import com.mypackage.ekart.dbservice.service.LineItemService;
import com.mypackage.ekart.dbservice.service.OrderService;
import com.mypackage.ekart.dbservice.util.ObjectConverter;

@Component
public class OrderServiceImpl implements OrderService {
	private OrderRepository repository;
	private LineItemService lineItemService;

	public OrderServiceImpl(OrderRepository repository, LineItemService lineItemService) {
		this.repository= repository;
		this.lineItemService= lineItemService;
	}

	@Override
	public OrderDTO submitOrder(Order order) {
		List<LineItem> lineItems= order.getLineItems();
		
		if(lineItems!= null && lineItems.size()>0){
			for (LineItem lineItem : lineItems) {
				lineItemService.addLineItem(lineItem);
			}
			
		}
		
		Order savedOrder= repository.save(order);
		OrderDTO orderDTO= null;
		
		if(savedOrder!= null){
			orderDTO= new OrderDTO();
			ObjectConverter.orderToOrderDTO(savedOrder, orderDTO);
		}

		return orderDTO;
	}

}
