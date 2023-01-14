package com.accenture.codingtest.springbootcodingtest.entity.exception;

public class RecordAlreadyExistException extends RecordException {

	public RecordAlreadyExistException() {
		super("EXC-0001", "Record Already Exist");
	}

	public RecordAlreadyExistException(String value) {
		this();
		this.put("key", value);
	}

}