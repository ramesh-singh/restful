package com.mypackage.ekart.dbservice.resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mypackage.ekart.dbservice.dto.OrderDTO;
import com.mypackage.ekart.dbservice.model.Order;
import com.mypackage.ekart.dbservice.service.OrderService;

@RestController
@RequestMapping(value= "/rest/db/orders")
public class OrderResource {
	private OrderService orderService;
	
	public OrderResource(OrderService orderService) {
		this.orderService= orderService;
	}
	
	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> submitOrder(@RequestBody Order order, UriComponentsBuilder builder){
		OrderDTO orderDTO= orderService.submitOrder(order);
		
		UriComponents components= builder.path("/rest/db/orders/{id}").buildAndExpand(orderDTO.getOrderId());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(components.toUri());
		return new ResponseEntity<OrderDTO>(orderDTO, responseHeaders, HttpStatus.CREATED);
	}

}
