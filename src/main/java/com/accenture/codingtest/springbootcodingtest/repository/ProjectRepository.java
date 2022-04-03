package com.accenture.codingtest.springbootcodingtest.repository;


import org.springframework.data.repository.CrudRepository;

import com.accenture.codingtest.springbootcodingtest.entity.Project;



public interface ProjectRepository extends CrudRepository<Project, String> {
}
