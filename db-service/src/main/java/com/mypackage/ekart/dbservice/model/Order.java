package com.mypackage.ekart.dbservice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "ORDER_DETAILS")
public class Order {
	
	@Id
	@Column(name= "ORDER_ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long orderId;
	
	@Column(name= "ORDER_TOTAL", nullable= false)
	private Double total;
	
	@Column(name= "ORDER_DATE", nullable= false)
	private Date date;
	
	@OneToOne
	@JoinColumn(name= "CUSTOMER_ID", nullable= false)
	private Customer customer;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "ORDER_ID")
	private List<LineItem> lineItems;
	
	@Column(name= "CANCEL_STATUS")
	private boolean cancel;
	
	public Long getOrderId() {
		return orderId;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", total=" + total + ", date="
				+ date + ", customer=" + customer + ", lineItems=" + lineItems
				+ ", cancel=" +cancel+"]";
	}
	
	@Override
	public boolean equals(Object obj){
		if((obj instanceof Order) && ((Order)obj).getOrderId().equals(this.orderId)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = (int)(prime * result + orderId);
		return result;
	}
	
	
	

}
