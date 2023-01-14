package com.accenture.codingtest.springbootcodingtest.entity.exception;

public class RecordException extends BusinessException {

	public RecordException(String code, String message) {
		super(code, message);
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder(super.getMessage());
		if (this.getParams().containsKey("key")) {
			builder.append(" (");
			builder.append(this.getParams().get("key"));
			builder.append(")");
		}

		return builder.toString();
	}

}