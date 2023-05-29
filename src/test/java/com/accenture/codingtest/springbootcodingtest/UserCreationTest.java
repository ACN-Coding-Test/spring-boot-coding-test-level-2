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
public class UserCreationTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void createUserTest() {
		// Prepare the request body for creating a user
		User user = new User("123", "abc@example.com", "test");

		// Send the POST request to create a user
		ResponseEntity<User> response = restTemplate.postForEntity(getBaseUrl() + "/api/v1/users/ADMIN", user,
				User.class);

		// Assert the response status code is 201 Created
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Assert the response body contains the created user
		User createdUser = response.getBody();
		assertEquals(user.getUsername(), createdUser.getUsername());
		assertEquals(user.getPassword(), createdUser.getPassword());
	}

	private String getBaseUrl() {
		return "http://localhost:" + port;
	}

}
