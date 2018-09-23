package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Project;

public interface ProjectService {
	
	public abstract Project getProject(int id);
	public abstract List<Project> getAllProjects();
	public abstract Project addProject(Project proj);
	public abstract Project editProject(int id, Project proj);
	public abstract Boolean removeProject(int id, Project proj);

}
