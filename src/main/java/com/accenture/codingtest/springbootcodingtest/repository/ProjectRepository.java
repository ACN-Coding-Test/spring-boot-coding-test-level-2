package com.accenture.codingtest.springbootcodingtest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.codingtest.springbootcodingtest.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	Page<Project> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
