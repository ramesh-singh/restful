package com.mypackage.ekart.dbservice.resource;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value= "/{orderId}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO getOrderById(@PathVariable Long orderId){
		return orderService.getOrderById(orderId);
	}
	
	@RequestMapping(method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> getAllOrders(){
		return orderService.getAllOrders();	
	}
	
	@DeleteMapping(value= "/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
		orderService.deleteOrder(orderId);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{orderId}", method= RequestMethod.PUT)
	public ResponseEntity<?> updateOrder(@RequestBody Order order, @PathVariable Long orderId){
		orderService.updateOrder(order, orderId);
		return ResponseEntity.noContent().build();
		
	}

}
