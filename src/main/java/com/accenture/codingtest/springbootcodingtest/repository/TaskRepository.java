package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.Task;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, String> {

	List<Task> findAll();

	List<Task> findByProjectId(String projectId);

	List<Task> findByUserId(String userId);

	Optional<Task> findById(String id);

}