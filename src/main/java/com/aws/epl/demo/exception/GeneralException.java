package com.aws.epl.demo.exception;

public class GeneralException  extends RuntimeException {
	private static final long serialVersionUID = 1L;


	public GeneralException(String errorMessage) {
		super(errorMessage);
	}

	public GeneralException(String errorMessage, Throwable ex) {
		super(errorMessage, ex);
	}

}
