package com.accenture.codingtest.springbootcodingtest;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateUser() {
        String createUserUrl = "/api/v1/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("admin", "admin");

        String requestBody = """
                                {
                                    "username":"Ram",
                                    "password":"1234"
                                }
                              """;
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        URI uri = UriComponentsBuilder.fromUriString(createUserUrl).build().toUri();

        ResponseEntity<User> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                User.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        User createdUser = response.getBody();
        assertEquals("Ram", createdUser.getUsername());
    }


}
