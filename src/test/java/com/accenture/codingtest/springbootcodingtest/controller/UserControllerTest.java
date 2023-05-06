package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.UserDTO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private static final String SECURED_URL = "http://localhost:9292/api/v1/users";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @org.junit.jupiter.api.Test
    public void saveUserTest() {
        UserDTO newUser = new UserDTO("test", "password@Test", "MEMBER");
        ResponseEntity<User> createdUser = testRestTemplate
                .withBasicAuth("admin", "password@123")
                .postForEntity(SECURED_URL, newUser, User.class);

        assertEquals(HttpStatus.CREATED, createdUser.getStatusCode());
    }
}