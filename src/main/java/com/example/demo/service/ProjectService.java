package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjectDao;
import com.example.demo.model.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectDao projDao;
	
	public Project getProject(int id) {
		Optional<Project> optProj = projDao.findById(id);
		if(!optProj.isPresent()) {
			return null;
		}else {
			return optProj.get();
		}
	}
	
	public List<Project> getAllProjects(){
		Iterable<Project> source = projDao.findAll();
		List<Project> listProj = new ArrayList<>();
		source.forEach(listProj::add);
		
		return listProj;
	}
	
	public Project addProject(Project proj) {
		return projDao.save(proj);
	}
	
	public Project editProject(int id, Project proj) {
		Optional<Project> projOnDb = projDao.findById(id);
		if(!projOnDb.isPresent()) {
			return null;
		}else {
			Project instanceProj = projOnDb.get();
			BeanUtils.copyProperties(proj, instanceProj, "id");
			return instanceProj = projDao.save(instanceProj);			
		}
	}
	
	public Boolean removeProject(int id, Project proj) {
		Optional<Project> projOnDb = projDao.findById(id);
		if (!projOnDb.isPresent()) {
			return false;
		}else {
			projDao.delete(proj);
			return true;
		}
		
	}
	
}
