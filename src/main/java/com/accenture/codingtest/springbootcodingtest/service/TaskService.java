package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.Users;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Task task, UUID userId) {
        Users users = userRepository.findById(userId).orElse(null);
        Task tasks = taskRepository.getTasksByUserId(userId);
        task.setUser(users);
        if(Objects.isNull(users)) {
            throw new ServiceException("User does not exist");
        }
        else if(Objects.isNull(tasks)) {
            taskRepository.save(task);
        } else {
            throw new ServiceException("Task is already assigned to a user");
        }
        return task;
    }

    public Task getTasksOfUser(UUID userId) {
        return taskRepository.getTasksByUserId(userId);
    }

    public List<Task> getTaskByProjectId(UUID projectId) {
        return taskRepository.getTasksByProjectId(projectId);
    }


}
