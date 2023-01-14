package com.accenture.codingtest.springbootcodingtest.entity.exception;

public class RecordNotFoundException extends RecordException {

	public RecordNotFoundException() {
		super("EXC-0002", "Record Not Found");
	}

	public RecordNotFoundException(String value) {
		this();
		this.put("key", value);
	}

}