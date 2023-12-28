package com.aws.epl.demo.exception;


public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -557494610930104418L;
	
	private String message;
	
	public StudentNotFoundException(String message) {
		super();
		this.message=message;
		
	}
	
	public String getMessage() {
		return message;
	}
}
