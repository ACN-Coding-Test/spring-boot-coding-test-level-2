package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.model.TaskDTO;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    private ResponseEntity<List<TaskDTO>> getTaskList() {
        if (!taskService.getAllTasks().isEmpty()) {
            return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/tasks/{task_id}")
    private ResponseEntity<TaskDTO> getTask(@PathVariable("task_id") UUID taskId) {
        TaskDTO task = taskService.getTask(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    private ResponseEntity<TaskDTO> saveTask(@RequestBody @Valid TaskDTO taskDTO) {
        return new ResponseEntity<>(taskService.createTask(taskDTO), HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{task_id}")
    private ResponseEntity<TaskDTO> updateTaskByPUT(@PathVariable("task_id") UUID taskId, @RequestBody @Valid TaskDTO taskDTO) {
        return new ResponseEntity<>(taskService.updateTaskByPUT(taskId, taskDTO), HttpStatus.OK);
    }

    @PatchMapping("/tasks/{task_id}")
    private ResponseEntity<?> updateTaskByPATCH(@PathVariable("task_id") UUID taskId, @RequestBody TaskDTO taskDTO) {
        return taskService.updateTaskByPatch(taskId, taskDTO);
    }

    @DeleteMapping("/tasks/{task_id}")
    private ResponseEntity<?> deleteTask(@PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Project deleted successfully!", HttpStatus.OK);
    }
}
