package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.TaskStatusEnum;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.entity.exception.RecordNotFoundException;
import com.accenture.codingtest.springbootcodingtest.entity.model.TaskDto;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import com.accenture.codingtest.springbootcodingtest.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@PostMapping("")
	public TaskDto create(@RequestBody TaskDto taskDto) {
		Task task = taskService.save(this.convertTaskDtoToTaskEntity(taskDto));
		return this.convertTaskEntityToTaskDto(task);
	}

	@PatchMapping("/{id}")
	public TaskDto update(@RequestBody TaskDto taskDto, @PathVariable String id) {
		Task task = taskService.update(this.convertTaskDtoToTaskEntity(taskDto), id);
		return this.convertTaskEntityToTaskDto(task);
	}

	@GetMapping("")
	public List<TaskDto> findAll() {
		return taskService.findAll().stream().map(this::convertTaskEntityToTaskDto).collect(Collectors.toList());
	}

	@GetMapping("/project/{projectId}")
	public List<TaskDto> findByProjectId(@PathVariable String projectId) {
		return taskService.findByProjectId(projectId).stream().map(this::convertTaskEntityToTaskDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/user/{userId}")
	public List<TaskDto> findByUserId(@PathVariable String userId) {
		return taskService.findByUserId(userId).stream().map(this::convertTaskEntityToTaskDto)
				.collect(Collectors.toList());
	}

	private TaskDto convertTaskEntityToTaskDto(Task task) {
		TaskDto taskDto = new TaskDto();
		taskDto.setId(task.getId());
		taskDto.setTitle(task.getTitle());
		taskDto.setDescription(task.getDescription());
		taskDto.setStatus(task.getStatus().toString());
		taskDto.setProjectId(task.getProject().getId());
		taskDto.setProjectName(task.getProject().getName());
		taskDto.setUserId(task.getUser().getId());
		taskDto.setUsername(task.getUser().getUsername());

		return taskDto;
	}

	private Task convertTaskDtoToTaskEntity(TaskDto taskDto) {
		Task task = new Task();
		task.setId(taskDto.getId());
		task.setTitle(taskDto.getTitle());
		task.setDescription(taskDto.getDescription());
		task.setStatus(TaskStatusEnum.valueOf(taskDto.getStatus()));

		User user = userService.findByUserId(taskDto.getUserId()).orElseThrow(RecordNotFoundException::new);
		Project project = projectService.findById(taskDto.getProjectId()).orElseThrow(RecordNotFoundException::new);

		task.setUser(user);
		task.setProject(project);

		return task;
	}

}