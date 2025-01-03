package com.example.exception;

public class StadiumNotFoundException extends RuntimeException{
	public StadiumNotFoundException(String msg) {
		super(msg);
	}
	
}
