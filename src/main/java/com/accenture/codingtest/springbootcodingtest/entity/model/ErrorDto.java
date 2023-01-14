package com.accenture.codingtest.springbootcodingtest.entity.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ErrorDto {

	private static final long serialVersionUID = 1L;

	private String type;

	private String code;

	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String host;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map params;

	public ErrorDto() {

	}

	public ErrorDto(String type, String code, String message, Map params) {
		this.type = type;
		this.code = code;
		this.message = message;
		this.params = params;
	}

	public ErrorDto(String type, String code, String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}

}