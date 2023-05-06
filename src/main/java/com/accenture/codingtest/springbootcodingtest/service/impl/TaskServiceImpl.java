package com.accenture.codingtest.springbootcodingtest.service.impl;

import com.accenture.codingtest.springbootcodingtest.model.TaskDTO;
import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import com.accenture.codingtest.springbootcodingtest.util.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),
                        task.getStatus().name(), task.getProject().getId(), task.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(UUID taskId) {
        return taskRepository.findById(taskId)
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),
                        task.getStatus().name(), task.getProject().getId(), task.getUser().getId()))
                .orElseThrow(() -> new NoSuchElementException("Task was not found with ID : " + taskId));
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        User user = userRepository.findById(taskDTO.getUserId()).orElseThrow(() -> new NoSuchElementException("User was not found with ID : " + taskDTO.getUserId()));
        Project project = projectRepository.findById(taskDTO.getProjectId()).orElseThrow(() -> new NoSuchElementException("Project was not found with ID : " + taskDTO.getProjectId()));
        Task task = taskRepository.save(new Task(taskDTO.getTitle(), taskDTO.getDescription(), project, user));
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),
                task.getStatus().name(), task.getProject().getId(), task.getUser().getId());
    }

    @Override
    public TaskDTO updateTaskByPUT(UUID taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task was not found with ID : " + taskId));
        User user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User was not found with ID : " + taskDTO.getUserId()));
        Project project = projectRepository.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("Project was not found with ID : " + taskDTO.getProjectId()));
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
        task.setProject(project);
        task.setUser(user);

        Task updatedTask = taskRepository.save(task);
        return new TaskDTO(updatedTask.getId(), updatedTask.getTitle(), updatedTask.getDescription(),
                updatedTask.getStatus().name(), updatedTask.getProject().getId(), updatedTask.getUser().getId());
    }

    @Override
    public ResponseEntity<?> updateTaskByPatch(UUID taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task was not found with ID : " + taskId));
        if (Objects.nonNull(taskDTO.getTitle())) {
            if (!taskDTO.getTitle().trim().isEmpty()) {
                task.setTitle(taskDTO.getTitle());
            } else {
                return new ResponseEntity<>("task title should not be empty", HttpStatus.BAD_REQUEST);
            }
        }

        task.setDescription(taskDTO.getDescription());

        if (Objects.nonNull(taskDTO.getStatus())) {
            if (!taskDTO.getStatus().trim().isEmpty()) {
                task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
            } else {
                return new ResponseEntity<>("task status should not be empty", HttpStatus.BAD_REQUEST);
            }
        }

        if (Objects.nonNull(taskDTO.getProjectId())) {
            Project project = projectRepository.findById(taskDTO.getProjectId())
                    .orElseThrow(() -> new NoSuchElementException("Project was not found with ID : " + taskDTO.getProjectId()));
            task.setProject(project);
        }

        if (Objects.nonNull(taskDTO.getUserId())) {
            User user = userRepository.findById(taskDTO.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("User was not found with ID : " + taskDTO.getUserId()));
            task.setUser(user);
        }
        Task updatedTask = taskRepository.save(task);
        return new ResponseEntity<>(new TaskDTO(updatedTask.getId(), updatedTask.getTitle(), updatedTask.getDescription(),
                updatedTask.getStatus().name(), updatedTask.getProject().getId(), updatedTask.getUser().getId()), HttpStatus.OK);
    }

    @Override
    public void deleteTask(UUID taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EmptyResultDataAccessException("Project was not found with ID : " + taskId, 0);
        }
        taskRepository.deleteById(taskId);
    }
}
