package com.accenture.codingtest.springbootcodingtest.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
	public class IDGenerate    
	{      
	//generates random UUID 

	 public UUID GenerateId()
	  {
		UUID uuid=UUID.randomUUID();  
		return uuid;
	  }  
	}

