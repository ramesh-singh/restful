package com.mypackage.ekart.dbservice.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mypackage.ekart.dbservice.model.Customer;
import com.mypackage.ekart.dbservice.repository.CustomerRepository;

@RestController
@RequestMapping("/rest/db/customers")
public class CustomerResource {
	private CustomerRepository customerRepository;
	
	public CustomerResource(CustomerRepository customerDAO){
		this.customerRepository= customerDAO;
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder){
		
		Customer savedCustomer= customerRepository.save(customer);
		UriComponents components= builder.path("rest/db/customers/{id}").buildAndExpand(savedCustomer.getId());
		return ResponseEntity.created(components.toUri()).build();
		
	}
	
	@GetMapping
	public List<Customer> getCustomers(){
		System.out.println("**********Inside getCustomer() method");
		return customerRepository.findAll();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("id") Long id){
		return customerRepository.getOne(id);
	}
	
	
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id, UriComponentsBuilder builder){
		Customer fetchedCustomer= customerRepository.getOne(id);
		fetchedCustomer.setFirstName(customer.getFirstName());
		fetchedCustomer.setLastName(customer.getLastName());
		fetchedCustomer.setStreet(customer.getStreet());
		fetchedCustomer.setCity(customer.getCity());
		fetchedCustomer.setState(customer.getState());
		fetchedCustomer.setCountry(customer.getCountry());
	
		customerRepository.save(fetchedCustomer);
		
		UriComponents components= builder.path("rest/db/customers/{id}").buildAndExpand(id);
		return ResponseEntity.created(components.toUri()).build();
	}
	

}
