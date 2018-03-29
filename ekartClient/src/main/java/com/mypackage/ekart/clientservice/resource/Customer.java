package com.mypackage.ekart.clientservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value= "/rest/ekart/customers")
public class Customer {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value="/{customerId}")
	public Object getCustomerById(@PathVariable Long customerId){
		return restTemplate.exchange("http://db-service/rest/db/customers/"+customerId,
				HttpMethod.GET,
				null, new ParameterizedTypeReference<Object>() {
		}).getBody();

	}
}

