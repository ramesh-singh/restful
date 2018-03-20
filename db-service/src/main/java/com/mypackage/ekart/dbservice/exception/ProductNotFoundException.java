package com.mypackage.ekart.dbservice.exception;

public class ProductNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6206655662102914481L;
	
	public ProductNotFoundException(String exception) {
		super(exception);
	}

}
