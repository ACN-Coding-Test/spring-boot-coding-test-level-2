package com.accenture.codingtest.springbootcodingtest.entity.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class BusinessException extends RuntimeException {

	private final String code;
	private Map<String, String> params = new HashMap<>();

	public BusinessException(String code) {
		this.code = code;
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(String code, Map<String, String> params) {
		this.code = code;
		this.params = params;
	}

	public BusinessException(String code, String message, Map<String, String> params) {
		super(message);
		this.code = code;
		this.params = params;
	}

	public void put(String key, String value) {
		this.params.put(key, value);
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(this.code);
		builder.append("]");
		if (StringUtils.hasText(super.getMessage())) {
			builder.append(" - ");
			builder.append(super.getMessage());
		}

		return builder.toString();
	}

	public String getOriginalMessage() {
		return super.getMessage();
	}

	public String getCode() {
		return this.code;
	}

	public Map<String, String> getParams() {
		return this.params;
	}

}