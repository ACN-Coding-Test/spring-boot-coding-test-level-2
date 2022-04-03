package com.accenture.codingtest.springbootcodingtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
	
@Service
public class CodingTestService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TaskRepository taskRepository;

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(user -> users.add(user));
		return users;
	}

	public User getUserById(String id) {
		return userRepository.findById(id).get();
	}

	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}

	public void updateById(User user, String id) {
		Optional<User> userRes = userRepository.findById(id);
		if (userRes.isPresent()) {
			userRes.get().setPassword(user.getPassword());
			userRes.get().setUserName(user.getUserName());
			userRepository.save(userRes.get());
		}

	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}

	public List<Project> getProjects() {
		List<Project> projects = new ArrayList<Project>();
		projectRepository.findAll().forEach(project -> projects.add(project));
		return projects;
	}

	public void saveOrUpdate(Project project) {
		projectRepository.save(project);
	}

	public void updateById(Project project, String id) {
		Optional<Project> projectRes = projectRepository.findById(id);
		if (projectRes.isPresent()) {
			projectRes.get().setName(project.getName());
			projectRepository.save(projectRes.get());
		}
	}

	public Project getProjectById(String id) {
		return projectRepository.findById(id).get();
	}

	public void deleteProject(String id) {
		projectRepository.deleteById(id);
	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		taskRepository.findAll().forEach(task -> tasks.add(task));
		return tasks;
	}
	public List<Task> getTasksByUserId(String userId) {
		List <Task> taskRes = taskRepository.findByUserId(userId);
		List<Task> tasks = new ArrayList<Task>();
		if (!taskRes.isEmpty()) {
			taskRes.forEach(task -> tasks.add(task));
		}
		return tasks;
	}

	public void saveOrUpdate(Task task) {
		taskRepository.save(task);
	}

	public void updateById(Task task, String id) {
		Optional<Task> taskRes = taskRepository.findById(id);
		if (taskRes.isPresent()) {
			taskRes.get().setStatus(task.getStatus());
			taskRes.get().setDescription(task.getDescription());
			taskRes.get().setTitle(task.getTitle());
			taskRes.get().setProjectId(task.getProjectId());
			taskRes.get().setUserId(task.getUserId());
			taskRepository.save(taskRes.get());
		}
	}
	public void updateById(Task task, String id,String userId) {
		Optional<Task> taskRes = taskRepository.findById(id);
		if (taskRes.isPresent()) {
			if(taskRes.get().getUserId().equalsIgnoreCase(userId)) {
				taskRes.get().setStatus(task.getStatus());
				taskRepository.save(taskRes.get());
			}
		}
	}


	public Task getTaskById(String id) {
		return taskRepository.findById(id).get();
	}

	public void deleteTask(String id) {
		taskRepository.deleteById(id);
	}
}