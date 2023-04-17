package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.model.Project;
import com.accenture.codingtest.springbootcodingtest.model.Task;
import com.accenture.codingtest.springbootcodingtest.model.User;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;
	private UserRepository userRepository;
	private ProjectRepository projectRepository;

	public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository,
			ProjectRepository projectRepository) {
		super();
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}

	@Override
	public List<Task> findAll() {

		return taskRepository.findAll();
	}

	@Override
	public Optional<Task> findById(UUID id) {

		return taskRepository.findById(id);
	}

	@Override
	public void createTask(Task task) {

		taskRepository.save(task);

	}

	@Override
	public void deleteTask(UUID id) {

		Optional<Task> usOptional = taskRepository.findById(id);
		taskRepository.delete(usOptional.get());

	}

	@Override
	public void patchTask(Task task, UUID id) {

		Optional<Task> usOptional = taskRepository.findById(id);
		Task task2 = usOptional.get();
		task2.setDescription(task.getDescription());
		task2.setStatus(task2.getStatus());
		task2.setTitle(task2.getTitle());
		Optional<Project> pOptional = projectRepository.findById(task.getProject_id().getId());
		task2.setProject_id(pOptional.get());
		Optional<User> uOptional = userRepository.findById(task.getUser_id().getId());
		task2.setUser_id(uOptional.get());
		taskRepository.save(task2);

	}

	@Override
	public void updateTask(Task task, UUID id) {

		Optional<Task> usOptional = taskRepository.findById(id);
		Task task2 = usOptional.get();
		task2.setDescription(task.getDescription());
		task2.setStatus(task2.getStatus());
		task2.setTitle(task2.getTitle());
		Optional<Project> pOptional = projectRepository.findById(task.getProject_id().getId());
		task2.setProject_id(pOptional.get());
		Optional<User> uOptional = userRepository.findById(task.getUser_id().getId());
		task2.setUser_id(uOptional.get());
		taskRepository.save(task2);

	}

}
