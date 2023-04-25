package com.accenture.codingtest.springbootcodingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.codingtest.springbootcodingtest.entity.TaskStatusMD;

public interface TaskStatusRepo extends JpaRepository<TaskStatusMD, Integer>{

	public void deleteByStatus(String taskStatus);

}
