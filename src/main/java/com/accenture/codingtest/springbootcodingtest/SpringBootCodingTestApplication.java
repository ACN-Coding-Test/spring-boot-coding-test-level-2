package com.accenture.codingtest.springbootcodingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.accenture.codingtest.springbootcodingtest")
public class SpringBootCodingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCodingTestApplication.class, args);
	}

}
