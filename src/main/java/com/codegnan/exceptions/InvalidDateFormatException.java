package com.codegnan.exceptions;

public class InvalidDateFormatException extends Exception {
	
	public InvalidDateFormatException() {
		
	}
	
	public InvalidDateFormatException(String message) {
		super(message);
	}
	
	public InvalidDateFormatException(Throwable cause) {
		super(cause);
	}
	
	public InvalidDateFormatException(String message,Throwable cause) {
		super(message,cause);
	}

}
