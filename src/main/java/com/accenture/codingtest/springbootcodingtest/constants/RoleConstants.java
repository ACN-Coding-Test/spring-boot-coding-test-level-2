package com.accenture.codingtest.springbootcodingtest.constants;

import org.springframework.stereotype.Component;

@Component
public class RoleConstants {

	  public static final String DEFAULT_ROLE = "ROLE_USER";
	  public static final String[] ADMIN_ACCESS = {"ROLE_ADMIN" ,"ROLE_PRODUCT_OWNER"};
	  public static final String[] PRODUCT_OWNER_ACCESS = {"ROLE_PRODUCT_OWNER"};
}
