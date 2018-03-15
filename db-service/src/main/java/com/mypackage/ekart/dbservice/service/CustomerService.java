package com.mypackage.ekart.dbservice.service;

import java.util.List;

import com.mypackage.ekart.dbservice.dto.CustomerDTO;


public interface CustomerService {
	//public Customer addCustomer(Customer customer);
	public List<CustomerDTO> getAllCustomers();
	public CustomerDTO getCustomerById(Long id);
	//public CustomerDTO updateCustomer(Customer customer);
	//public CustomerDTO deleteCustomer(Long id); 
	

}
