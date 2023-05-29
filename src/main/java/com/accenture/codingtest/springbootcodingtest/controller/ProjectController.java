package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.model.Role;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController {
	@Autowired
    private ProjectService projectService;

    @GetMapping("/v1/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/v1/projects/{role}")
    public  ResponseEntity<Project> saveProject(@RequestBody Project project,
                                                @PathVariable("role") String role) {
        ResponseEntity<Project> response;
        if(Role.ADMIN.toString().equalsIgnoreCase(role)) {
            response = projectService.saveProject(project);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return  response;
    }

    @GetMapping("/v1/projects/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("project_id") String project_id) {
        return projectService.getProjectById(project_id);
    }

    @PutMapping("/v1/projects")
    public  ResponseEntity<Project> updateProject(@RequestBody Project project) {
        return  projectService.updateProject(project);
    }

    @DeleteMapping("/v1/projects/{project_id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable("project_id") String project_id) {
        return projectService.deleteProject(project_id);
    }
    
    @GetMapping
	public Page<Project> getProjects(@RequestParam(value = "q", required = false) String searchKeyword, @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex, @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "name") String sortBy, @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection) {

		Sort.Direction direction = Sort.Direction.ASC;
		if (sortDirection.equalsIgnoreCase("DESC")) {
			direction = Sort.Direction.DESC;
		}

		Sort sort = Sort.by(direction, sortBy);
		PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, sort);

		if (searchKeyword != null && !searchKeyword.isEmpty()) {
			return projectService.searchProjectsByName(searchKeyword, pageRequest);
		} else {
			return projectService.getAllProjects(pageRequest);
		}
	}

}
