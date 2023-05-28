package com.accenture.codingtest.springbootcodingtest.repository;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
