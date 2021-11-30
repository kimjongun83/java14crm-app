package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.dto.ProjectDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Project;

public class ProjectRepository {
	
	public List<Project> findAll(){
		List<Project> projects = new ArrayList<Project>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM projects";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getNString("name"));
				project.setDescription(resultSet.getNString("description"));
				project.setStartDate(resultSet.getDate("start_date"));
				project.setEndDate(resultSet.getDate("end_date"));
				project.setUserId(resultSet.getInt("user_id"));
				
				projects.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projects;
	}
	public List<ProjectDto> getAll(){
		List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM projects INNER JOIN users ON projects.user_id = users.id";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ProjectDto projectDto = new ProjectDto();
				projectDto.setId(resultSet.getInt("id"));
				projectDto.setName(resultSet.getNString("name"));
				projectDto.setDescription(resultSet.getNString("description"));
				projectDto.setStartDate(resultSet.getDate("start_date"));
				projectDto.setEndDate(resultSet.getDate("end_date"));
				projectDto.setUserId(resultSet.getInt("user_id"));
				projectDto.setUserName(resultSet.getNString("users.name"));
				
				projectDtos.add(projectDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projectDtos;
	}
	public boolean addProject(Project project) {
		Connection connection = DbConnection.getConnection();
		String query = "INSERT INTO projects(name, description, start_date, end_date, user_id) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, project.getName());
			statement.setNString(2, project.getDescription());
			statement.setDate(3, project.getStartDate());
			statement.setDate(4, project.getEndDate());
			statement.setInt(5, project.getUserId());
			
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Project getProjectById(int id) {
		Project entity ;
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM projects WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				entity = new Project();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getNString("name"));
				entity.setDescription(resultSet.getNString("description"));
				entity.setStartDate(resultSet.getDate("start_date"));
				entity.setEndDate(resultSet.getDate("end_date"));
				entity.setUserId(resultSet.getInt("user_id"));
				
				return entity;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public boolean updateProject(int id, Project entity) {
		Connection connection = DbConnection.getConnection();
		String query = "UPDATE projects SET name = ?, description = ?, start_date = ?, end_date = ?, user_id = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, entity.getName());
			statement.setNString(2, entity.getDescription());
			statement.setDate(3, entity.getStartDate());
			statement.setDate(4, entity.getEndDate());
			statement.setInt(5, entity.getUserId());
			statement.setInt(6, id);
			
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean deleteProject(int id) {
		Connection connection  = DbConnection.getConnection();
		String query = "DELETE FROM projects WHERE id = ?";
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
	public List<UserDto> getAllUserProject(int idProject){
		List<UserDto> listUserDto = new ArrayList<UserDto>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM projects INNER JOIN tasks ON projects.id = tasks.project_id INNER JOIN users ON tasks.user_id = users.id WHERE projects.id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idProject);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				UserDto userDto = new UserDto();
				userDto.setId(resultSet.getInt("users.id"));
				userDto.setEmail(resultSet.getString("users.email"));
				userDto.setPassWord(resultSet.getString("users.password"));
				userDto.setFullName(resultSet.getNString("users.name"));
				userDto.setAvatar(resultSet.getString("users.avatar"));
				userDto.setRoleId(resultSet.getInt("users.role_id"));
				
				listUserDto.add(userDto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUserDto;
	}
}
