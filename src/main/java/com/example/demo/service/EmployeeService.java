package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;

public interface EmployeeService {
	
	public abstract Employee getEmployee (int id);
	public abstract List<Employee> getAllEmployee();
	public abstract Employee addEmployee(Employee emp);
	public abstract Employee editEmployee(int id, Employee emp);
	public abstract Boolean removeEmployee(int id, Employee emp);
	public abstract Boolean addProject(Employee emp, Project proj);
	public abstract List<Employee> getEmployeeDoubleProject();

}
