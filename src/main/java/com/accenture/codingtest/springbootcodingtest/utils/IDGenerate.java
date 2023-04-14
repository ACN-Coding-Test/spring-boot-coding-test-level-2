package com.accenture.codingtest.springbootcodingtest.utils;

import java.util.UUID;    
public class IDGenerate    
{      
//generates random UUID 
 public UUID GenerateId()
  {
	UUID uuid=UUID.randomUUID();  
	return uuid;
  }  
}
 
