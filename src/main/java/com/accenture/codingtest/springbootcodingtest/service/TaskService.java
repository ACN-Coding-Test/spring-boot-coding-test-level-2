package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.TaskStatus;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.projectService = projectService;
    }

    public Task findById(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with Id: " + taskId));
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {

        assignProject(task);

        assignUser(task);

        return taskRepository.save(task);
    }

    private void assignUser(Task task) {
        UUID userId = task.getUser().getId();
        User user = userService.findById(userId);
        task.setUser(user);
    }

    private void assignProject(Task task) {
        UUID projectId = task.getProject().getId();
        Project project = projectService.findById(projectId);
        task.setProject(project);
    }

    public Task updateTask(UUID taskId, Task task) {
        Task fetch = findById(taskId);


        fetch.setTitle(task.getTitle());
        fetch.setDescription(task.getDescription());
        fetch.setStatus(task.getStatus());
        assignProject(task);
        assignUser(task);

        return taskRepository.save(fetch);
    }

    public Task patchTask(UUID taskId, Task task) {
        Task fetch = findById(taskId);

        if (task.getTitle() != null) {
            fetch.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            fetch.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            fetch.setStatus(task.getStatus());
        }
        if (task.getProject() != null) {
            assignProject(task);
        }
        if (task.getUser() != null) {
            assignUser(task);
        }

        return taskRepository.save(fetch);
    }

    public void delete(UUID taskId) {
        Task fetch = findById(taskId);
        taskRepository.delete(fetch);
    }


    public Task updateTaskStatus(UUID taskId, TaskStatus status) {
        Task fetch = findById(taskId);
        patchTask(taskId,fetch).setStatus(status);
        return null;
    }
}
