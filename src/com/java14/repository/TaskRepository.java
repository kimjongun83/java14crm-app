package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.dto.StatusDto;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Status;
import com.myclass.entity.Task;

public class TaskRepository {

	public List<Task> findAll(){
		List<Task> tasks = new ArrayList<Task>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM tasks";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Task entity = new Task();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getNString("name"));
				entity.setDescription(resultSet.getNString("description"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				entity.setStatusId(resultSet.getInt("status_id"));
				entity.setUserId(resultSet.getInt("user_id"));
				entity.setProjectId(resultSet.getInt("project_id"));
				
				tasks.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tasks;
	}
	public List<TaskDto> getAll(){
		List<TaskDto> taskDtos = new ArrayList<TaskDto>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM tasks INNER JOIN users ON tasks.user_id = users.id INNER JOIN status ON tasks.status_id = status.id";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(resultSet.getInt("id"));
				taskDto.setName(resultSet.getNString("name"));
				taskDto.setDescription(resultSet.getNString("description"));
				taskDto.setStartDate(resultSet.getDate("start_date"));
				taskDto.setEndDate(resultSet.getDate("end_date"));
				taskDto.setStatusId(resultSet.getInt("status_id"));
				taskDto.setUserId(resultSet.getInt("user_id"));
				taskDto.setProjectId(resultSet.getInt("project_id"));
				
				taskDto.setUserName(resultSet.getNString("users.name"));
				taskDto.setStatus(resultSet.getNString("status.name"));
				
				taskDtos.add(taskDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskDtos;
	}
	public boolean addTask(Task task) {
		Connection connection = DbConnection.getConnection();
		String query = "INSERT INTO tasks(name, description, start_date, end_date, status_id, user_id, project_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, task.getName());
			statement.setNString(2, task.getDescription());
			statement.setDate(3, task.getStartDate());
			statement.setDate(4, task.getEndDate());
			statement.setInt(5, task.getStatusId());
			statement.setInt(6, task.getUserId());
			statement.setInt(7, task.getProjectId());
			
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	public Task getTaskById(int id) {
		Task entity;
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM tasks WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				entity = new Task();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getNString("name"));
				entity.setDescription(resultSet.getNString("description"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				entity.setStatusId(resultSet.getInt("status_id"));
				entity.setUserId(resultSet.getInt("user_id"));
				entity.setProjectId(resultSet.getInt("project_id"));
				
				return entity;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	public boolean updateTask(int id, Task entity) {
		Connection connection = DbConnection.getConnection();
		String query = "UPDATE tasks SET name = ?, description = ?, start_date = ?, end_date = ?, status_id = ?, user_id = ?, project_id = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, entity.getName());
			statement.setNString(2, entity.getDescription());
			statement.setDate(3, entity.getStartDate());
			statement.setDate(4, entity.getEndDate());
			statement.setInt(5, entity.getStatusId());
			statement.setInt(6, entity.getUserId());
			statement.setInt(7, entity.getProjectId());
			statement.setInt(8, id);
			
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean deleteTask(int id) {
		Connection connection = DbConnection.getConnection();
		String query = "DELETE FROM tasks WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<StatusDto> getAllStatus(){
		List<StatusDto> listStatus = new ArrayList<StatusDto>();
		StatusDto statusDto;
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM status";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				statusDto = new StatusDto();
				statusDto.setId(resultSet.getInt("id"));
				statusDto.setName(resultSet.getNString("name"));
				
				listStatus.add(statusDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStatus;
	}
}
