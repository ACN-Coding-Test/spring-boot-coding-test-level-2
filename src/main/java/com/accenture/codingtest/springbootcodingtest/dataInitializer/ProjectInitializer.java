package com.accenture.codingtest.springbootcodingtest.dataInitializer;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProjectInitializer implements CommandLineRunner {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectInitializer(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) {

        Project project1 = new Project();
        project1.setName("project1");
        projectRepository.save(project1);

        Project project2 = new Project();
        project2.setName("project2");
        projectRepository.save(project2);

        Project project3 = new Project();
        project3.setName("project3");
        projectRepository.save(project3);

        Project project4 = new Project();
        project4.setName("project4");
        projectRepository.save(project4);

        Project project5 = new Project();
        project5.setName("project5");
        projectRepository.save(project5);

        Project project6 = new Project();
        project6.setName("project6");
        projectRepository.save(project6);

        Project project7 = new Project();
        project7.setName("project7");
        projectRepository.save(project7);

        Project project8 = new Project();
        project8.setName("project8");
        projectRepository.save(project8);

        Project project9 = new Project();
        project9.setName("project9");
        projectRepository.save(project9);

        Project project10 = new Project();
        project10.setName("project10");
        projectRepository.save(project10);

        Project project11 = new Project();
        project11.setName("project11");
        projectRepository.save(project11);

        Project project12 = new Project();
        project12.setName("project12");
        projectRepository.save(project12);

        Project project13 = new Project();
        project13.setName("project13");
        projectRepository.save(project13);

        Project project14 = new Project();
        project14.setName("project14");
        projectRepository.save(project14);

        Project project15 = new Project();
        project15.setName("project15");
        projectRepository.save(project15);


    }
}
