package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projService;

	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable int id) {
		if (projService.getProject(id) == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(projService.getProject(id));
		}
	}

	@GetMapping
	public List<Project> list() {
		return projService.getAllProjects();
	}

	@PostMapping
	public ResponseEntity<Project> add(@Valid @RequestBody Project proj) {
		try {
			if(projService.addProject(proj).equals(null)) {
				return ResponseEntity.badRequest().build();
			}else {
				return ResponseEntity.ok(projService.addProject(proj));
			}
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Project> editProject(@Valid @PathVariable int id, @RequestBody Project proj) {
		try {
			if (projService.editProject(id, proj) == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(projService.editProject(id, proj));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Project> removeEmployee(@PathVariable int id, Project proj) {
		if (projService.removeProject(id, proj) == false) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
