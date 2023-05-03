package com.accenture.codingtest.springbootcodingtest;

import com.accenture.codingtest.springbootcodingtest.model.ProjectDto;
import com.accenture.codingtest.springbootcodingtest.model.TaskDto;
import com.accenture.codingtest.springbootcodingtest.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.client.RestTemplate;


public class TestRestTemplate extends SpringBootCodingTestApplication {
    public static final String BASE_URL = "http://localhost:8080";
    RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testcase(){
        UserDto userDto =new UserDto();
        userDto.setUsername("user1");
        userDto.setPassword("password");
        restTemplate.postForEntity(BASE_URL+"/api/v1/users",userDto, UserDto.class);

        ProjectDto projectDto =new ProjectDto();
        projectDto.setName("project1");
        restTemplate.postForEntity(BASE_URL+"/api/v1/projects",projectDto, UserDto.class);

    }
}
