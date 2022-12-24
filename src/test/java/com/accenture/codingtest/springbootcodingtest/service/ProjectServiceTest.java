package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Users;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService subject;

    @Mock
    private ProjectRepository projectRepository;

    @Test
    public void createProject() {
        Project project = new Project();
        project.setId(UUID.fromString("a27c8583-2968-4f18-a1e6-b138a7d6c522"));
        project.setName("API development");

        Project result = subject.createNewProject(project, "PRODUCT_OWNER");
        assertEquals(result, project);
    }
}
