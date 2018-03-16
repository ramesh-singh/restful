package com.mypackage.ekart.dbservice.resource;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mypackage.ekart.dbservice.dto.CustomerDTO;
import com.mypackage.ekart.dbservice.model.Customer;
import com.mypackage.ekart.dbservice.service.CustomerService;

@RestController
@RequestMapping("/rest/db/customers")
public class CustomerResource {
	private CustomerService customerService;

	public CustomerResource(CustomerService customerService){
		this.customerService= customerService;
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer customer, UriComponentsBuilder builder){

		CustomerDTO savedCustomer= customerService.addCustomer(customer);
		UriComponents components= builder.path("/rest/db/customers/{id}").buildAndExpand(savedCustomer.getCustomerId());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(components.toUri());
		return new ResponseEntity<CustomerDTO>(savedCustomer, responseHeaders, HttpStatus.CREATED);

	}

	@RequestMapping(value="timeout", method = RequestMethod.GET)
	public String getFoobar() throws InterruptedException {
		Thread.sleep(200000);
		return "foo";
	}

	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerDTO> getAllCustomers(){
		return customerService.getAllCustomers();
	}


	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public CustomerDTO getCustomerById(@PathVariable("id") Long id){
		return customerService.getCustomerById(id);
	}


	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id){
		customerService.updateCustomer(customer, id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
		customerService.deleteCustomer(id);;
		return ResponseEntity.noContent().build();
	}


}
