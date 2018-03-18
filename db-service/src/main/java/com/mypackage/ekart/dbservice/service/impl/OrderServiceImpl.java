package com.mypackage.ekart.dbservice.service.impl;

import java.util.ArrayList;
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

	@Override
	public OrderDTO getOrderById(Long orderId) {
		Order fetchedOrder= repository.getOne(orderId);
		OrderDTO orderDTO= null;
		
		if(fetchedOrder!= null){
			orderDTO= new OrderDTO();
			ObjectConverter.orderToOrderDTO(fetchedOrder, orderDTO);
		}
		
		return orderDTO;
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		List<Order> fetchedOrders= repository.findAll();
		List<OrderDTO> orderDTOs= null;
		
		if(fetchedOrders!= null){
			orderDTOs= new ArrayList<OrderDTO>();
			
			for(Order order: fetchedOrders){
				OrderDTO orderDTO= new OrderDTO();
				ObjectConverter.orderToOrderDTO(order, orderDTO);
				orderDTOs.add(orderDTO);
			}
		}
		return orderDTOs;
	}

	@Override
	public void deleteOrder(Long orderId) {
		repository.delete(repository.getOne(orderId));
	}

	@Override
	public void updateOrder(Order order, Long orderId) {
		Order fetchedOrder= repository.getOne(orderId);
		
		if(fetchedOrder!= null){
			fetchedOrder.setTotal(order.getTotal());
			fetchedOrder.setDate(order.getDate());
			fetchedOrder.setCustomer(order.getCustomer());
			fetchedOrder.setCancel(order.isCancel());
			fetchedOrder.setLineItems(order.getLineItems());
			repository.save(fetchedOrder);
		}
		
	}

}
