package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
	@SequenceGenerator(name="emp_generator", sequenceName = "seq_employee", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@NotNull
    @Size(min = 2, max = 300)
	private String name;
	
	private BigDecimal salary;
	
	@ManyToMany(fetch =FetchType.EAGER)
	@JoinTable(
			name = "tb_emp_proj",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
			
	)
	private List<Project> projects;	
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public Employee() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	public Boolean checkProjectName(String nameProject) {
		for (Project project : projects) {
			if(project.getName().equals(nameProject)) {
				return false;
			}
		}
		return true;
	}
}
