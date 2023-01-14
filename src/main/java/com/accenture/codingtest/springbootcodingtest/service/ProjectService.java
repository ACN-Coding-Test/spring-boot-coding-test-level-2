package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;

import com.accenture.codingtest.springbootcodingtest.entity.Project;

public interface ProjectService {

	Project save(Project project);

	List<Project> findAll();

	Optional<Project> findById(String id);

}