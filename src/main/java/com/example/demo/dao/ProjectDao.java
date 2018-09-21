package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Project;

public interface ProjectDao extends CrudRepository<Project, Integer>{

}
