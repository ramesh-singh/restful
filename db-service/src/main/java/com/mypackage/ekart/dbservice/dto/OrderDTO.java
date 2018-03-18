package com.mypackage.ekart.dbservice.dto;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class OrderDTO extends ResourceSupport{
	private Long orderId;
	private Double total;
	private Date date;
	private CustomerDTO customerDTO;
	private List<LineItemDTO> lineItemDTOs;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public CustomerDTO getCustomer() {
		return customerDTO;
	}
	public void setCustomer(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	public List<LineItemDTO> getLineItems() {
		return lineItemDTOs;
	}
	public void setLineItems(List<LineItemDTO> lineItemDTOs) {
		this.lineItemDTOs = lineItemDTOs;
	}

}
