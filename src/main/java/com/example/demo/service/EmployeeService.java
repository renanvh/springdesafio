package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
@Service
public class EmployeeService {

	@Autowired
	EmployeeDao empDao;
	
	public Employee getEmployee(int id) {
		Optional<Employee> optEmp = empDao.findById(id);
		if(!optEmp.isPresent()) {
			return null;
		}else {
			return optEmp.get();
		}
	}
	
	public List<Employee> getAllEmployee(){
		Iterable<Employee> source = empDao.findAll();
		List<Employee> listEmp = new ArrayList<>();
		source.forEach(listEmp::add);
		
		return listEmp;
	}
	
	public Employee addEmployee(Employee emp) {
		return empDao.save(emp);
	}
	
	public Employee editEmployee(int id, Employee emp) {
		Optional<Employee> empOnDb = empDao.findById(id);
		if(!empOnDb.isPresent()) {
			return null;
		}else {
			Employee instanceEmp = empOnDb.get();
			BeanUtils.copyProperties(emp, instanceEmp, "id");
			return instanceEmp = empDao.save(instanceEmp);			
		}
	}
	
	public Boolean removeEmployee(int id, Employee emp) {
		Optional<Employee> empOnDb = empDao.findById(id);
		if (!empOnDb.isPresent()) {
			return false;
		}else {
			empDao.delete(emp);
			return true;
		}
		
	}
}
