package com.travelservice.exceptions;

public class EntryAlreadyExistsException extends TSException{

	private static final long serialVersionUID = -1199077558911201528L;

	public EntryAlreadyExistsException(String key) {
		super(key);
	}
}
