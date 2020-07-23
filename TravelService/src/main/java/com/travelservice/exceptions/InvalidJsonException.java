package com.travelservice.exceptions;

public class InvalidJsonException extends TSException {

	private static final long serialVersionUID = -6557633424113073890L;

	public InvalidJsonException(String key) {
		super(key);
	}
}
