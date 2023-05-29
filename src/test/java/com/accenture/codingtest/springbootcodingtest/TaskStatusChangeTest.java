package com.accenture.codingtest.springbootcodingtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accenture.codingtest.springbootcodingtest.entity.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskStatusChangeTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void changeTaskStatusTest() {
		// Create a user
		User user = new User("123", "test@example.com", "test");
		ResponseEntity<User> createUserResponse = restTemplate.postForEntity(getBaseUrl() + "/api/v1/users/ADMIN", user,
				User.class);
		assertEquals(HttpStatus.OK, createUserResponse.getStatusCode());

	}

	private String getBaseUrl() {
		return "http://localhost:" + port;
	}
}
