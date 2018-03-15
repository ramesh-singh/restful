package com.mypackage.ekart.dbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "CUSTOMER_DETAILS")
public class Customer{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="CUSTOMER_ID")
	private Long customerId;
	
	@Column(name="FIRSTNAME", nullable=false, length=30)
	private String firstName;
	
	@Column(name="LASTNAME", nullable=true, length=30)
	private String lastName;
	
	@Column(name="STREET", nullable=false, length=30)
	private String street;
	
	@Column(name="CITY", nullable=false, length=30)
	private String city;
	
	@Column(name= "STATE", nullable= false, length=30)
	private String state;
	
	@Column(name="ZIPCODE", nullable=false, length=10)
	private String zip;
	
	@Column(name="COUNTRY", nullable=false, length=30)
	private String country;
	
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	@Override
	public String toString() {
		return "Costomer [customerId=" + customerId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", street=" + street + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", country="
				+ country + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int)(prime * result + customerId);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Customer && ((Customer)obj).customerId==this.customerId){
			return true;
		}else{
			return false;
		}
	}
	
	

}
