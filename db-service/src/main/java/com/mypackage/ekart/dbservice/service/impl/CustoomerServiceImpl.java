package com.mypackage.ekart.dbservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.cache.annotation.CacheResult;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.mypackage.ekart.dbservice.dto.CustomerDTO;
import com.mypackage.ekart.dbservice.exception.CustomerNotFoundExcpetion;
import com.mypackage.ekart.dbservice.model.Customer;
import com.mypackage.ekart.dbservice.repository.CustomerRepository;
import com.mypackage.ekart.dbservice.service.CustomerService;
import com.mypackage.ekart.dbservice.util.ObjectConverter;

@Service
public class CustoomerServiceImpl implements CustomerService {
	private CustomerRepository repository;


	public CustoomerServiceImpl(CustomerRepository repository) {
		this.repository= repository;
	}


	@Override
	@CacheResult
	public List<CustomerDTO> getAllCustomers() {
		List<CustomerDTO> customerDTOs= null;
		try{
			List<Customer> customers= repository.findAll();


			if(customers!= null && customers.size()>0){
				customerDTOs= new ArrayList<CustomerDTO>();
				for(Customer customer: customers){
					CustomerDTO customerDTO= new CustomerDTO();
					ObjectConverter.customerToCustomerDTO(customer, customerDTO);
					customerDTOs.add(customerDTO);
				}
			}
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
		return customerDTOs;
	}

	@Override
	@CacheResult
	public CustomerDTO getCustomerById(Long customerId) {
		Customer customer= null;
		CustomerDTO customerDTO= null;

		try{
			System.out.println("Retrieving Customer from database.");
			customer= repository.getOne(customerId);
			customerDTO= new CustomerDTO();
			ObjectConverter.customerToCustomerDTO(customer, customerDTO);
		}catch(EntityNotFoundException e){
			throw new CustomerNotFoundExcpetion("Customer with customer id: "+customerId+ 
					" does not exist. Please try again with correct customer id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}
		return customerDTO;
	}

	@Override
	public CustomerDTO addCustomer(Customer customer) {
		CustomerDTO customerDTO= null;
		try{
			Customer savedCustomer= repository.save(customer);

			if(savedCustomer!= null){

				customerDTO= new CustomerDTO();
				ObjectConverter.customerToCustomerDTO(savedCustomer, customerDTO);
			}
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}

		return customerDTO;
	}

	@Override
	public void updateCustomer(Customer customer, Long customerId) {
		try{
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
		}catch(EntityNotFoundException e){
			throw new CustomerNotFoundExcpetion("Customer with customer id: "+customerId+ 
					" does not exist. Please try again with correct customer id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}

	}

	@Override
	public void deleteCustomer(Long customerId) {
		try{
		Customer customer= repository.getOne(customerId);
		customer.getCity();                                                   //Invoking to catch EntityNotFoundException.
		repository.delete(customer);
		}catch(EntityNotFoundException e){
			throw new CustomerNotFoundExcpetion("Customer with customer id: "+customerId+ 
					" does not exist. Please try again with correct customer id.");
		}catch(RuntimeException e){
			throw new RuntimeException("Something went wrong. Please try again later.");
		}

	}
}
