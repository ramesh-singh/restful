package com.mypackage.ekart.dbservice.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.mypackage.ekart.dbservice.dto.CustomerDTO;
import com.mypackage.ekart.dbservice.dto.LineItemDTO;
import com.mypackage.ekart.dbservice.dto.OrderDTO;
import com.mypackage.ekart.dbservice.dto.ProductDTO;
import com.mypackage.ekart.dbservice.model.Customer;
import com.mypackage.ekart.dbservice.model.LineItem;
import com.mypackage.ekart.dbservice.model.Order;
import com.mypackage.ekart.dbservice.model.Product;
import com.mypackage.ekart.dbservice.resource.CustomerResource;
import com.mypackage.ekart.dbservice.resource.OrderResource;
import com.mypackage.ekart.dbservice.resource.ProductResource;

public class ObjectConverter {
	
	public static void orderToOrderDTO(Order order, OrderDTO orderDTO){
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setTotal(order.getTotal());
		orderDTO.setDate(order.getDate());
		CustomerDTO customerDTO= new CustomerDTO();
		customerToCustomerDTO(order.getCustomer(), customerDTO);
		orderDTO.setCustomer(customerDTO);
		List<LineItem> lineItems= order.getLineItems();
		List<LineItemDTO> lineItemDTOs= null;
		if(lineItems!= null && lineItems.size()>0){
			lineItemDTOs= new ArrayList<LineItemDTO>();
			
			for(LineItem lineItem: lineItems){
				LineItemDTO lineItemDTO= new LineItemDTO();
				convertLineItemToLineItemDTO(lineItem, lineItemDTO);
				lineItemDTOs.add(lineItemDTO);
			}
		}
		orderDTO.setLineItems(lineItemDTOs);
		Link selfLink= ControllerLinkBuilder.linkTo(OrderResource.class).slash(orderDTO.getOrderId()).withSelfRel();
		Link customerLink= ControllerLinkBuilder.linkTo(CustomerResource.class).slash(customerDTO.getCustomerId()).withRel("customer");
		orderDTO.add(selfLink);
		orderDTO.add(customerLink);
	}
	
	public static void customerToCustomerDTO(Customer customer,
			CustomerDTO customerDTO) {
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setStreet(customer.getStreet());
		customerDTO.setCity(customer.getCity());
		customerDTO.setState(customer.getState());
		customerDTO.setCountry(customer.getCountry());
		customerDTO.setZip(customer.getZip());
		Link selfLink= ControllerLinkBuilder.linkTo(CustomerResource.class).slash(customerDTO.getCustomerId()).withSelfRel();
		customerDTO.add(selfLink);
	}
	
	public static void convertProductToProductDTO(Product product, ProductDTO productDTO){
		productDTO.setProductId(product.getProductId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setPrice(product.getPrice());
		Link selfLink= ControllerLinkBuilder.linkTo(ProductResource.class).slash(productDTO.getProductId()).withSelfRel();
		productDTO.add(selfLink);
	}
	
	public static void convertLineItemToLineItemDTO(LineItem lineItem, LineItemDTO lineItemDTO){
		lineItemDTO.setLineItemId(lineItem.getLineItemId());
		lineItemDTO.setQuantity(lineItem.getQuantity());
		ProductDTO productDTO= new ProductDTO();
		convertProductToProductDTO(lineItem.getProduct(), productDTO);
		lineItemDTO.setProduct(productDTO);
	}

}
