package com.mypackage.ekart.dbservice.service;

import java.util.List;

import com.mypackage.ekart.dbservice.dto.CustomerDTO;
import com.mypackage.ekart.dbservice.model.Customer;


public interface CustomerService {
	public CustomerDTO addCustomer(Customer customer);
	public List<CustomerDTO> getAllCustomers();
	public CustomerDTO getCustomerById(Long customerId);
	public void updateCustomer(Customer customer, Long customerId);
	public void deleteCustomer(Long customerId); 
	

}
