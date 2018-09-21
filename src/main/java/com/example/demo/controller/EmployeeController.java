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

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
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
	public Employee add(@Valid @RequestBody Employee emp) {
		return empService.addEmployee(emp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> editEmployee(@Valid @PathVariable int id, @RequestBody Employee emp) {
		 if(empService.editEmployee(id, emp) == null) {
			 return ResponseEntity.notFound().build();
		 }else {
			 return ResponseEntity.ok(empService.getEmployee(id));
		 }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> removeEmployee(@PathVariable int id, Employee emp) {
		if(empService.removeEmployee(id, emp) == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.noContent().build();
		}
	}
}
