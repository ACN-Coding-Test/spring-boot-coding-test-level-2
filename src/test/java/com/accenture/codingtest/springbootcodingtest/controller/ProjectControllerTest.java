package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateProjectWithTwoUsers() {
        Project project = projectRepository.save(new Project("Test-Project"));
        User user1 = userRepository.save(new User("user-1", "password@User1", "MEMBER"));
        User user2 = userRepository.save(new User("user-2", "password@User2", "MEMBER"));

        Set<User> userList = new HashSet<>();
        userList.add(user1);
        userList.add(user2);
        project.setUser(userList);
        Project userAddedProject = projectRepository.save(project);

        assertEquals(2, userAddedProject.getUser().size());
    }
}