package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjectDao;
import com.example.demo.model.Project;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectDao projDao;
	
	@Override
	public Project getProject(int id) {
		Optional<Project> optProj = projDao.findById(id);
		if(!optProj.isPresent()) {
			return null;
		}else {
			return optProj.get();
		}
	}
	
	@Override
	public List<Project> getAllProjects(){
		Iterable<Project> source = projDao.findAll();
		List<Project> listProj = new ArrayList<>();
		source.forEach(listProj::add);
		
		return listProj;
	}
	
	@Override
	public Project addProject(Project proj) {
		return projDao.save(proj);
	}
	
	@Override
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
	
	@Override
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
