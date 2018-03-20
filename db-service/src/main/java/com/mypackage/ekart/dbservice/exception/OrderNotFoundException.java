package com.mypackage.ekart.dbservice.exception;

public class OrderNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7488905351310472815L;
	public OrderNotFoundException(String exception) {
		super(exception);
	}

}
