package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, UUID> {

}
