package com.accenture.codingtest.springbootcodingtest;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@SpringBootApplication
public class SpringBootCodingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCodingTestApplication.class, args);



    }

}
