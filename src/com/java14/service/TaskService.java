package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.StatusDto;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Project;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.repository.ProjectRepository;
import com.myclass.repository.TaskRepository;
import com.myclass.repository.UserRepository;

public class TaskService {

	private TaskRepository taskRepository;
	private UserRepository userRepository;
	private ProjectRepository projectRepository;
	
	public TaskService() {
		taskRepository = new TaskRepository();
		userRepository = new UserRepository();
		projectRepository = new ProjectRepository();
	}
	
	public List<TaskDto> findAll(){
		List<TaskDto> taskDtos = new ArrayList<TaskDto>();
		List<Task> tasks = taskRepository.findAll();
		for (Task entity : tasks) {
			TaskDto taskDto = new TaskDto();
			taskDto.setId(entity.getId());
			taskDto.setName(entity.getName());
			taskDto.setDescription(entity.getDescription());
			taskDto.setStartDate(entity.getStartDate());
			taskDto.setEndDate(entity.getEndDate());
			taskDto.setStatusId(entity.getStatusId());
			taskDto.setUserId(entity.getUserId());
			taskDto.setProjectId(entity.getProjectId());
			
			taskDtos.add(taskDto);
		}
		return taskDtos;
	}
	public List<TaskDto> getAll(){
		return taskRepository.getAll();
	}
	public boolean addTaskDto(TaskDto taskDto) {
		if(checkTaskExist(taskDto)) {
			Task entity = new Task();
			convertToTask(entity, taskDto);
			return taskRepository.addTask(entity);
		}
		return false;
	}
	public TaskDto getTaskDtoById(int id) {
		TaskDto taskDto;
		Task entity = taskRepository.getTaskById(id);
		if(entity != null) {
			taskDto = new TaskDto();
			taskDto.setId(entity.getId());
			taskDto.setName(entity.getName());
			taskDto.setDescription(entity.getDescription());
			taskDto.setStartDate(entity.getStartDate());
			taskDto.setEndDate(entity.getEndDate());
			taskDto.setStatusId(entity.getStatusId());
			taskDto.setUserId(entity.getUserId());
			taskDto.setProjectId(entity.getProjectId());
			
			return taskDto;
		}
		return null;
	}
	public boolean updateTaskDto(int id, TaskDto taskDto) {
		if(checkTaskExist(taskDto)) {
			Task entity = taskRepository.getTaskById(id);
			convertToTask(entity, taskDto);
			return taskRepository.updateTask(id, entity);
		}
		return false;
	}
	public boolean deleteTaskDto(int id) {
		return taskRepository.deleteTask(id);
	}
	public List<StatusDto> getAllStatus(){
		return taskRepository.getAllStatus();
	}
	private boolean checkTaskExist(TaskDto taskDto) {
		if(taskDto.getName().equals("") || taskDto.getDescription().equals("") || taskDto.getUserId() == 0 || taskDto.getProjectId() == 0
															|| taskDto.getStatusId() == 0) {
			return false;
		}
		List<Task> tasks = taskRepository.findAll();
		for (Task entity : tasks) {
			if(taskDto.getName().equalsIgnoreCase(entity.getName()) && (taskDto.getId() != entity.getId())) {
				return false;
			}
		}
		if(!taskDto.getStartDate().before(taskDto.getEndDate())) {
			return false;
		}
		User user = userRepository.getUserById(taskDto.getUserId());
		if(user == null) {
			return false;
			
		}
		Project project = projectRepository.getProjectById(taskDto.getProjectId());
		if(project == null) {
			return false;
		}
		return true;
	}
	
	private void convertToTask(Task entity, TaskDto taskDto) {
		entity.setName(taskDto.getName());
		entity.setDescription(taskDto.getDescription());
		entity.setStartDate(taskDto.getStartDate());
		entity.setEndDate(taskDto.getEndDate());
		entity.setStatusId(taskDto.getStatusId());
		entity.setUserId(taskDto.getUserId());
		entity.setProjectId(taskDto.getProjectId());	
	}
}
