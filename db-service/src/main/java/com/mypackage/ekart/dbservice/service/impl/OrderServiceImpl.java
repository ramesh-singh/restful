package com.mypackage.ekart.dbservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.cache.annotation.CacheResult;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import com.mypackage.ekart.dbservice.dto.OrderDTO;
import com.mypackage.ekart.dbservice.exception.OrderNotFoundException;
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
		OrderDTO orderDTO= null;
		try{
			List<LineItem> lineItems= order.getLineItems();

			if(lineItems!= null && lineItems.size()>0){
				for (LineItem lineItem : lineItems) {
					lineItemService.addLineItem(lineItem);
				}
			}

			Order savedOrder= repository.save(order);

			if(savedOrder!= null){
				orderDTO= new OrderDTO();
				ObjectConverter.orderToOrderDTO(savedOrder, orderDTO);
			}
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}

		return orderDTO;
	}

	@Override
	@CacheResult
	public OrderDTO getOrderById(Long orderId) {
		OrderDTO orderDTO= null;
		try{
			Order fetchedOrder= repository.getOne(orderId);


			if(fetchedOrder!= null){
				orderDTO= new OrderDTO();
				ObjectConverter.orderToOrderDTO(fetchedOrder, orderDTO);
			}
		}catch(EntityNotFoundException e){
			throw new OrderNotFoundException("Order with order id: "+orderId+ 
					" does not exist. Please try again with correct order id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}

		return orderDTO;
	}

	@Override
	@CacheResult
	public List<OrderDTO> getAllOrders() {
		List<OrderDTO> orderDTOs= null;
		try{
			List<Order> fetchedOrders= repository.findAll();


			if(fetchedOrders!= null){
				orderDTOs= new ArrayList<OrderDTO>();

				for(Order order: fetchedOrders){
					OrderDTO orderDTO= new OrderDTO();
					ObjectConverter.orderToOrderDTO(order, orderDTO);
					orderDTOs.add(orderDTO);
				}
			}
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
		return orderDTOs;
	}

	@Override
	public void deleteOrder(Long orderId) {
		try{
			Order order= repository.getOne(orderId);
			order.getTotal();                                                  //Invoking to catch EntityNotFoundException.
			repository.delete(order);
		}catch(EntityNotFoundException e){
			throw new OrderNotFoundException("Order with order id: "+orderId+ 
					" does not exist. Please try again with correct order id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
	}

	@Override
	public void updateOrder(Order order, Long orderId) {
		try{
			Order fetchedOrder= repository.getOne(orderId);

			if(fetchedOrder!= null){
				fetchedOrder.setTotal(order.getTotal());
				fetchedOrder.setDate(order.getDate());
				fetchedOrder.setCustomer(order.getCustomer());
				fetchedOrder.setCancel(order.isCancel());
				fetchedOrder.setLineItems(order.getLineItems());
				repository.save(fetchedOrder);
			}
		}catch(EntityNotFoundException e){
			throw new OrderNotFoundException("Order with order id: "+orderId+ 
					" does not exist. Please try again with correct order id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}

	}

}
