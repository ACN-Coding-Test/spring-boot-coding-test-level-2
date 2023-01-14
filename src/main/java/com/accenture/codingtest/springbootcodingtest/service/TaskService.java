package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;

import com.accenture.codingtest.springbootcodingtest.entity.Task;

public interface TaskService {

    Task save(Task task);

    Optional<Task> findById(String id);

    List<Task> findAll();

    List<Task> findByProjectId(String projectId);

    List<Task> findByUserId(String userId);

    Task update(Task task, String id);

}