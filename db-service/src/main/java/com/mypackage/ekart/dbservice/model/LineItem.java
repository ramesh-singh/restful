package com.mypackage.ekart.dbservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "LINEITEM_DETAILS")
public class LineItem implements Serializable{
	
	private static final long serialVersionUID = -3460925459924675327L;

	@Id
	@Column(name= "LINEITEM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long lineItemId;
	
	@Column(name= "QUANTITY", nullable= false)
	private int quantity;
	
	@OneToOne
	@JoinColumn(name= "PRODUCT_ID", nullable= false)
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

	@Override
	public String toString() {
		return "LineItem [lineItemId=" + lineItemId + ", quantity=" + quantity
				+ ", product=" + product + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lineItemId == null) ? 0 : lineItemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItem other = (LineItem) obj;
		if (lineItemId == null) {
			if (other.lineItemId != null)
				return false;
		} else if (!lineItemId.equals(other.lineItemId))
			return false;
		return true;
	}
	
	
	

}
