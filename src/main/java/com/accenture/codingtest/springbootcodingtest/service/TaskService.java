package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.Users;
import com.accenture.codingtest.springbootcodingtest.enums.Roles;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.accenture.codingtest.springbootcodingtest.constants.TaskStatus.NOT_STARTED;
import static com.accenture.codingtest.springbootcodingtest.constants.TaskStatus.STATUSES;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Task createTask(Task task, String role) {
        if(StringUtils.equals(Roles.PRODUCT_OWNER.toString(), role)) {
            Users users = userRepository.findAll().stream().findAny().orElse(null);
            Task tasks = taskRepository.findTasksByUserId(task.getUser().getId());
            if(Objects.isNull(users)) {
                task.setUser(task.getUser());
                userRepository.save(task.getUser());
            }
            task.setStatus(NOT_STARTED);
            taskRepository.save(task);
            if(Objects.isNull(tasks)) {
                taskRepository.save(task);
            } else {
                throw new ServiceException("Task is already assigned to a user");
            }
        }
        return task;
    }

    public Task updateStatus(Task task, UUID userId) {
        Task taskEntity = taskRepository.findTasksByUserId(userId);
        if(STATUSES.contains(task.getStatus())) {
            taskEntity.setStatus(task.getStatus());
            taskRepository.save(taskEntity);
        } else {
            throw new ServiceException("Status is invalid");
        }
        return taskEntity;
    }

    public Task getTasksOfUser(UUID userId) {
        return taskRepository.findTasksByUserId(userId);
    }

    public List<Task> getTaskByProjectId(UUID projectId) {
        return taskRepository.getTasksByProjectId(projectId);
    }


}
