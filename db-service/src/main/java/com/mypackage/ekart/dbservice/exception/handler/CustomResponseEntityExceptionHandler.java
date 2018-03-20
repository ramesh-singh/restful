package com.mypackage.ekart.dbservice.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mypackage.ekart.dbservice.error.ErrorResponse;
import com.mypackage.ekart.dbservice.exception.CustomerNotFoundExcpetion;
import com.mypackage.ekart.dbservice.exception.OrderNotFoundException;
import com.mypackage.ekart.dbservice.exception.ProductNotFoundException;


@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomerNotFoundExcpetion.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundExcpetion ex, WebRequest request){
		ErrorResponse errorResponse= new ErrorResponse(new Date(), "404", HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request){
		ErrorResponse errorResponse= new ErrorResponse(new Date(), "500", HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> ProductNotFoundException(ProductNotFoundException ex, WebRequest request){
		ErrorResponse errorResponse= new ErrorResponse(new Date(), "404", HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> OrderNotFoundException(OrderNotFoundException ex, WebRequest request){
		ErrorResponse errorResponse= new ErrorResponse(new Date(), "404", HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	

}
