package com.mypackage.ekart.dbservice.dto;

import org.springframework.hateoas.ResourceSupport;

import com.mypackage.ekart.dbservice.model.Product;

public class LineItemDTO extends ResourceSupport{
	private Long lineItemId;
	private int quantity;
	private Product product;
	public Long getLineItemId() {
		return lineItemId;
	}
	public void setLineItemId(Long lineItemId) {
		this.lineItemId = lineItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
