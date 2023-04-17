package com.accenture.codingtest.springbootcodingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories("com.accenture.codingtest.springbootcodingtest.repository")
public class SpringBootCodingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCodingTestApplication.class, args);
	}

}
