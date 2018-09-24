package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.service.EmployeeServiceImpl;
import com.example.demo.service.ProjectServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdesafioApplicationTests {

	@Autowired
	private EmployeeServiceImpl empServ;

	@Autowired
	private EmployeeDao empDao;

	@Autowired
	private ProjectServiceImpl projServ;

	@Autowired
	private ProjectDao projDao;

	@Test
	public void testD() {
		Employee emp = makeEmp();
		Employee saved = empServ.addEmployee(emp);

		Employee getFromDb = empDao.findById(saved.getId()).get();

		assertThat(getFromDb.getId()).isEqualTo(saved.getId());
		assertThat(getFromDb.getName()).isEqualTo(saved.getName());
		assertThat(getFromDb.getSalary()).isEqualTo(saved.getSalary());

	}

	@Test
	public void testE() {
		Project proj = makeProj();
		Project saved = projServ.addProject(proj);

		Project getFromDb = projDao.findById(proj.getId()).get();

		assertThat(getFromDb.getId()).isEqualTo(saved.getId());
		assertThat(getFromDb.getName()).isEqualTo(saved.getName());
	}

	private Employee makeEmp() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("renan");
		emp.setSalary(BigDecimal.valueOf(123.45));
		return emp;
	}

	private Project makeProj() {
		Project proj = new Project();
		proj.setId(1);
		proj.setName("projeto1");
		return proj;
	}

}
