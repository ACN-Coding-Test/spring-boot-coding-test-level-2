package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.model.TaskDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getAllTasks();

    TaskDTO getTask(UUID taskId);

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTaskByPUT(UUID taskId, TaskDTO taskDTO);

    ResponseEntity<?> updateTaskByPatch(UUID taskId, TaskDTO taskDTO);

    void deleteTask(UUID taskId);
    Task updateStatus(UUID taskId, String status, UUID userId);
}
