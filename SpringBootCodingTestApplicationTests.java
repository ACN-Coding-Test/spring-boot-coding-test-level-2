package com.accenture.codingtest.springbootcodingtest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootCodingTestApplicationTests {

	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort = 8091;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testCreateUserSuccess() throws URISyntaxException
	{
		final String baseUrl = "http://localhost:" + randomServerPort+"/api/v1/users";
		System.out.println(baseUrl);
		URI uri = new URI(baseUrl);
		User user = new User(UUID.fromString("d865b31f-8a1a-46d2-b079-9de2b078bdb2"),"tpeter", "1234", "ROLE_USER");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-RAM-PERSIST", "true");

		HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);

		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity,
				String.class);

		// Verify request succeed
		Assert.assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testCreateProjectSuccess() throws URISyntaxException
	{
		final String baseUrl = "http://localhost:" + randomServerPort+"/api/v1/projects";
		System.out.println(baseUrl);
		URI uri = new URI(baseUrl);
		Project project = new Project(UUID.fromString("d865b31f-8a1a-46d2-b079-9de2b078bdb2"),"ASNB");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-RAM-PERSIST", "true");

		HttpEntity<Project> httpEntity = new HttpEntity<>(project, headers);

		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity,
				String.class);

		// Verify request succeed
		Assert.assertEquals(201, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void testUpdateTaskSuccess() throws URISyntaxException
	{
		final String baseUrl = "http://localhost:" + randomServerPort+"/api/v1/tasks/{id}";
		System.out.println(baseUrl);
		URI uri = new URI(baseUrl);
		Task task = new Task(UUID.fromString("0c5f0f9f-ffdb-4579-adf8-fee7d5962bf3"),"ASNB","ASNB","NOT_STARTED",
							UUID.fromString("d865b31f-8a1a-46d2-b079-9de2b078bdb1"),UUID.fromString("24023aeb-9ec8-4711-a923-1050658b8730"));

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-RAM-PERSIST", "true");

		HttpEntity<Task> httpEntity = new HttpEntity<>(task, headers);

		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, httpEntity,
				String.class);

		// Verify request succeed
		Assert.assertEquals(201, responseEntity.getStatusCodeValue());
	}

}

