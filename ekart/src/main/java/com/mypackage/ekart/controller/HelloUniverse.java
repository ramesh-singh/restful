package com.mypackage.ekart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloUniverse {
	
	@RequestMapping(value= "/hello", method=RequestMethod.GET)
	public String sendMessage(){
		return "Hello Universe!!!";
	}

}
