package com.mypackage.ekart.dbservice.dto;

import org.springframework.hateoas.ResourceSupport;

public class LineItemDTO extends ResourceSupport{
	private Long lineItemId;
	private int quantity;
	private ProductDTO productDTO;
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
	public ProductDTO getProduct() {
		return productDTO;
	}
	public void setProduct(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

}
