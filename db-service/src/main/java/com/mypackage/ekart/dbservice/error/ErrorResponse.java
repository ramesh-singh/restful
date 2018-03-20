package com.mypackage.ekart.dbservice.error;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private Date timestamp;
	private String status;
	private HttpStatus error;
	private String message;

	public ErrorResponse(Date timestamp, String status, HttpStatus error, String message) {
		this.timestamp= timestamp;
		this.status= status;
		this.error= error;
		this.message= message;	
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
