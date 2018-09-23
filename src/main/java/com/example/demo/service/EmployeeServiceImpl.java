package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao empDao;
	
	@Override
	public Employee getEmployee(int id) {
		Optional<Employee> optEmp = empDao.findById(id);
		if(!optEmp.isPresent()) {
			return null;
		}else {
			return optEmp.get();
		}
	}
	
	@Override
	public List<Employee> getAllEmployee(){
		Iterable<Employee> source = empDao.findAll();
		List<Employee> listEmp = new ArrayList<>();
		source.forEach(listEmp::add);
		
		return listEmp;
	}
	
	@Override
	public Employee addEmployee(Employee emp) {
		return empDao.save(emp);
	}
	
	@Override
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
	
	@Override
	public Boolean removeEmployee(int id, Employee emp) {
		Optional<Employee> empOnDb = empDao.findById(id);
		if (!empOnDb.isPresent()) {
			return false;
		}else {
			empDao.delete(emp);
			return true;
		}
		
	}
	
	@Override
	public Boolean addProject(Employee emp, Project proj) {
		if(emp.getProjects().contains(proj)) {
			return false;
		}else if(emp.getProjects().size() >= 2) {
			return false;
		}else {
			emp.getProjects().add(proj);
			
			Employee instanceEmp = empDao.findById(emp.getId()).get();
			BeanUtils.copyProperties(emp, instanceEmp, "id");
			
			empDao.save(instanceEmp);
			return true;	
		}
	}
	
	@Override
	public List<Employee> getEmployeeDoubleProject(){
		Iterable<Employee> source = empDao.findAll();
		List<Employee> listEmp = new ArrayList<>();
		for (Employee employee : source) {
			if(employee.getProjects().size()>1) {
				listEmp.add(employee);
			}
		}
		return listEmp;
	}
}
