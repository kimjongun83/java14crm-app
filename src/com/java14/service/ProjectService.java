package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.ProjectDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Project;
import com.myclass.repository.ProjectRepository;

public class ProjectService {
	
	private ProjectRepository projectRepository;
	
	public ProjectService() {
		projectRepository = new ProjectRepository();
	}
	
	public List<ProjectDto> findAll(){
		List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
		List<Project> projects = projectRepository.findAll();
		for (Project project : projects) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(project.getId());
			projectDto.setName(project.getName());
			projectDto.setDescription(project.getDescription());
			projectDto.setStartDate(project.getStartDate());
			projectDto.setEndDate(project.getEndDate());
			projectDto.setUserId(project.getUserId());
			
			projectDtos.add(projectDto);
			
		}
		
		return projectDtos;
	}
	public List<ProjectDto> getAll(){
		return projectRepository.getAll();
	}
	public boolean addProjectDto(ProjectDto projectDto) {
		if(checkProjectExist(projectDto)) {
			Project project = new Project();
			convertToProject(project, projectDto);
			return projectRepository.addProject(project);
		}
		return false;
	}
	public ProjectDto getProjectDtoById(int id) {
		Project entity = projectRepository.getProjectById(id);
		if(entity != null) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(entity.getId());
			projectDto.setName(entity.getName());
			projectDto.setDescription(entity.getDescription());
			projectDto.setStartDate(entity.getStartDate());
			projectDto.setEndDate(entity.getEndDate());
			projectDto.setUserId(entity.getUserId());
			
			return projectDto;
		}
		
		return null;
	}
	public boolean updateProjectDto(int id, ProjectDto projectDto) {
		if(checkProjectExist(projectDto)) {
			Project entity = projectRepository.getProjectById(id);
			convertToProject(entity, projectDto);
			return projectRepository.updateProject(id, entity);
		}

		return false;
	}
	public boolean deleteProjectDto(int id) {
		return projectRepository.deleteProject(id);
	}
	private void convertToProject(Project project, ProjectDto projectDto) {
		project.setName(projectDto.getName());
		project.setDescription(projectDto.getDescription());
		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());
		project.setUserId(projectDto.getUserId());
	}
	private boolean checkProjectExist(ProjectDto projectDto) {
		if(projectDto.getName().equalsIgnoreCase("")||projectDto.getDescription().equalsIgnoreCase("") || projectDto.getStartDate() == null
												|| projectDto.getEndDate() == null || projectDto.getUserId() == 0) {
			return false;
		}
		List<Project> projects = projectRepository.findAll();
		for (Project project : projects) {
			if(project.getName().equalsIgnoreCase(projectDto.getName()) && (project.getId() != projectDto.getId())) {
				return false;
			}
		}
		
		return true;
	}
	public List<UserDto> getAllUserProject(int idProject){
		return projectRepository.getAllUserProject(idProject);
	}
}
