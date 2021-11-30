package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.entity.Role;

public class RoleRepository {
	
	public List<Role> findAll(){
		List<Role> roles = new ArrayList<>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM roles";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getNString("name"));
				role.setDescription(resultSet.getNString("description"));
				
				roles.add(role);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}
	
	public boolean addRole(Role role) {
		Connection connection = DbConnection.getConnection();
		String query = "INSERT INTO roles(name, description) VALUES(?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, role.getName());
			statement.setNString(2, role.getDescription());
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Role getRoleById(int id) {
		Role role = new Role();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM roles WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getNString("name"));
				role.setDescription(resultSet.getNString("description"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
	}
	
	public boolean updateRole(int id, Role role) {
		Connection connection = DbConnection.getConnection();
		String query = "UPDATE  roles SET name = ?, description = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, role.getName());
			statement.setNString(2, role.getDescription());
			statement.setInt(3, id);
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteRole(int id) {
		Connection connection = DbConnection.getConnection();
		String query = "DELETE FROM roles WHERE id = ?";
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
}
