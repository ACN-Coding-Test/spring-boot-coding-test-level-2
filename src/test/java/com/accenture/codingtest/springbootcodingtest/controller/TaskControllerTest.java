package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import com.accenture.codingtest.springbootcodingtest.util.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskService taskService;

    @Test
    public void testChangeStatus() {
        Project project = projectRepository.save(new Project("Test-Project-2"));
        User user = userRepository.save(new User("user-3", "password@User3", "MEMBER"));

        Task createdTask = taskRepository.save(new Task("Login Page", "Create a new login page", project, user));

        assertEquals(createdTask.getStatus(), TaskStatus.NOT_STARTED);

        Task task = taskService.updateStatus(createdTask.getId(), "IN_PROGRESS", user.getId());

        assertEquals(task.getStatus(), TaskStatus.IN_PROGRESS);
    }
}