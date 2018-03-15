package com.mypackage.ekart.dbservice.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mypackage.ekart.dbservice.dto.CustomerDTO;
import com.mypackage.ekart.dbservice.service.CustomerService;

@RestController
@RequestMapping("/rest/db/customers")
public class CustomerResource {
	private CustomerService customerService;
	
	public CustomerResource(CustomerService customerService){
		this.customerService= customerService;
	}
	
	/*@PostMapping
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder){
		
		CustomerDTO savedCustomer= customerService.addCustomer(customer, builder);
		UriComponents components= builder.path("rest/db/customers/{id}").buildAndExpand(savedCustomer.getId());
		return ResponseEntity.created(components.toUri()).build();
		
	}*/
	
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerDTO> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public CustomerDTO getCustomerById(@PathVariable("id") Long id){
		return customerService.getCustomerById(id);
	}
	
	
	/*@RequestMapping(value="/{id}",method= RequestMethod.PUT)
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
	}*/
	

}
