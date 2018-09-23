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
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ProjectService projService;

	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		if(empService.getEmployee(id) == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(empService.getEmployee(id));
		}
	}
	
	@GetMapping
	public List<Employee> list(){
		return empService.getAllEmployee();
	}
	
	@PostMapping
	public ResponseEntity<Employee> add(@Valid @RequestBody Employee emp) {
		try {
			if(empService.addEmployee(emp).equals(null)) {
				return ResponseEntity.badRequest().build();
			}else {
				return ResponseEntity.ok(empService.addEmployee(emp));
			}
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> editEmployee(@Valid @PathVariable int id, @RequestBody Employee emp) {
		try {
			if(empService.editEmployee(id, emp) == null) {
				return ResponseEntity.notFound().build();
			}else {
				return ResponseEntity.ok(empService.editEmployee(id, emp));
			}
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> removeEmployee(@PathVariable int id, Employee emp) {
		if(empService.removeEmployee(id, emp) == false) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/{id}/project/{pid}")
	public ResponseEntity<Employee> addToProject(@Valid @PathVariable int id,
			@PathVariable int pid) {
		try {
			if((empService.getEmployee(id) == null) ||  (projService.getProject(pid) == null)) {
				return ResponseEntity.notFound().build();
			}
			else if(empService.addProject(empService.getEmployee(id),projService.getProject(pid))) {
				return ResponseEntity.ok(empService.getEmployee(id));
			}else {
				return ResponseEntity.badRequest().build();
			}
			
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/doubleprojects")
	public List<Employee> doubleProjects(){
		return empService.getEmployeeDoubleProject();
	}
}
