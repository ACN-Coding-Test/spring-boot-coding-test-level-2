package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.model.Project;
@Repository
public interface ProjectRepository
		extends JpaRepository<Project, UUID> {

}
