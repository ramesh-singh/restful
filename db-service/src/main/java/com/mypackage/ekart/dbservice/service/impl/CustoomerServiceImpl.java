package com.mypackage.ekart.dbservice.service.impl;

import java.util.ArrayList;
import java.util.List;





import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import com.mypackage.ekart.dbservice.dto.CustomerDTO;
import com.mypackage.ekart.dbservice.model.Customer;
import com.mypackage.ekart.dbservice.repository.CustomerRepository;
import com.mypackage.ekart.dbservice.resource.CustomerResource;
import com.mypackage.ekart.dbservice.service.CustomerService;

@Component
public class CustoomerServiceImpl implements CustomerService {
	private CustomerRepository repository;

	public CustoomerServiceImpl(CustomerRepository repository) {
		this.repository= repository;
	}


	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers= repository.findAll();
		List<CustomerDTO> customerDTOs= null;
		
		if(customers!= null && customers.size()>0){
			customerDTOs= new ArrayList<CustomerDTO>();
			for(Customer customer: customers){
				CustomerDTO customerDTO= new CustomerDTO();
				customerToCustomerDTO(customer, customerDTO);
				customerDTOs.add(customerDTO);
			}
		}
		return customerDTOs;	
	}
	
	@Override
	public CustomerDTO getCustomerById(Long customerId) {
		Customer customer= repository.getOne(customerId);
		CustomerDTO customerDTO= null;
		if(customer!= null){
			customerDTO= new CustomerDTO();
			customerToCustomerDTO(customer, customerDTO);
		}
		
		return customerDTO;
	}
	
	@Override
	public CustomerDTO addCustomer(Customer customer) {
		Customer savedCustomer= repository.save(customer);
		CustomerDTO customerDTO= null;
		if(savedCustomer!= null){
			customerDTO= new CustomerDTO();
			customerToCustomerDTO(savedCustomer, customerDTO);
		}
		
		return customerDTO;
	}
	
	@Override
	public void updateCustomer(Customer customer, Long customerId) {
		Customer fetchedCustomer= repository.getOne(customerId);
		
		if(fetchedCustomer!= null){
			fetchedCustomer.setFirstName(customer.getFirstName());
			fetchedCustomer.setLastName(customer.getLastName());
			fetchedCustomer.setStreet(customer.getStreet());
			fetchedCustomer.setCity(customer.getCity());
			fetchedCustomer.setState(customer.getState());
			fetchedCustomer.setCountry(customer.getCountry());
			fetchedCustomer.setZip(customer.getZip());
			repository.save(fetchedCustomer);
		}
		
	}
	
	@Override
	public void deleteCustomer(Long customerId) {
		repository.delete(repository.getOne(customerId));
		
	}


	private void customerToCustomerDTO(Customer customer,
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
}
