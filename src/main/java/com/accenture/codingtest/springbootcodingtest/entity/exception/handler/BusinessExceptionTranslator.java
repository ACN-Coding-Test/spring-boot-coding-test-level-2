package com.accenture.codingtest.springbootcodingtest.entity.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.accenture.codingtest.springbootcodingtest.entity.exception.BusinessException;
import com.accenture.codingtest.springbootcodingtest.entity.model.ErrorDto;

@ControllerAdvice
public class BusinessExceptionTranslator {

	public BusinessExceptionTranslator() {

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BusinessException.class })
	public ErrorDto handleBusinessException(BusinessException ex) {
		return new ErrorDto("BusinessException", ex.getCode(), ex.getMessage(), ex.getParams());
	}
}