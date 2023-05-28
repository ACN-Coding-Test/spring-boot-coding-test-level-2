package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<Project> saveProject(Project project) {
        ResponseEntity<Project> response = null;
        if (project != null) {
           response = new ResponseEntity<>(projectRepository.save(project), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      return response;
    }
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(projectRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Project> getProjectById(String id) {
        return new ResponseEntity<>(projectRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Project> updateProject(Project project) {
        ResponseEntity<Project> updatedProject = null;
        String id = project.getId();
        Optional<Project> oldProjectOp = projectRepository.findById(id);

        if(oldProjectOp.isPresent()) {
            Project oldProject = oldProjectOp.get();

            oldProject.setName(project.getName());
            // oldProject.setProject_id(project.getProject_id());

            updatedProject = new ResponseEntity<>(projectRepository.save(oldProject), HttpStatus.OK);
        } else {
            updatedProject = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return updatedProject;
    }

    public ResponseEntity<Void> deleteProject(String id) {
        ResponseEntity<Void> response = null;
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
    
}
