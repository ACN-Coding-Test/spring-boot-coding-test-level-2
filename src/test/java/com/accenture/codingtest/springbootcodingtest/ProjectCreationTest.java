package com.accenture.codingtest.springbootcodingtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accenture.codingtest.springbootcodingtest.entity.Project;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectCreationTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void createProjectAndAssignUsersTest() {
		// Prepare the request body for creating a project
		Project project = new Project("123", "Sample project");

		// Send the POST request to create a project
		ResponseEntity<Project> createProjectResponse = restTemplate
				.postForEntity(getBaseUrl() + "/api/v1/projects/ADMIN", project, Project.class);

		// Assert the response status code is 200 OK
		assertEquals(HttpStatus.OK, createProjectResponse.getStatusCode());

	}

	private String getBaseUrl() {
		return "http://localhost:" + port;
	}
}
