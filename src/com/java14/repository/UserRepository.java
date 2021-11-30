package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

import com.myclass.connection.DbConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserRepository {
	
	public List<User> findAll(){
		List<User> users = new ArrayList<User>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM users";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassWord(resultSet.getString("password"));
				user.setFullName(resultSet.getNString("name"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	public List<UserDto> findAllUserDto(){
		List<UserDto> userDtos = new ArrayList<UserDto>();
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM users INNER JOIN roles ON users.role_id = roles.id";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				UserDto userDto = new UserDto();
				userDto.setId(resultSet.getInt("id"));
				userDto.setEmail(resultSet.getString("email"));
				userDto.setPassWord(resultSet.getString("password"));
				userDto.setFullName(resultSet.getNString("name"));
				userDto.setAvatar(resultSet.getString("avatar"));
				userDto.setRoleId(resultSet.getInt("role_id"));
				userDto.setRoleName(resultSet.getNString("roles.name"));
				
				userDtos.add(userDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userDtos;
	}
	
	public boolean addUser(User user) {
		Connection connection = DbConnection.getConnection();
		String query = "INSERT INTO users(email, password, name, avatar, role_id) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassWord());
			statement.setNString(3, user.getFullName());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;		
	}
	
	public User getUserById(int id) {
		User user;
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM users WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassWord(resultSet.getString("password"));
				user.setFullName(resultSet.getNString("name"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean updateUser(int id, User user) {
		Connection connection = DbConnection.getConnection();
		String query = "UPDATE users SET email = ?, password = ?, name = ?, avatar = ?, role_id = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassWord());
			statement.setNString(3, user.getFullName());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, id);
			
			statement.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteUser(int id) {
		Connection connection = DbConnection.getConnection();
		String query = "DELETE FROM users WHERE id = ?";
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
	public User getUserByName(String name) {
		User entity;
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM users WHERE name = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				entity = new User();
				entity.setId(resultSet.getInt("id"));
				entity.setEmail(resultSet.getString("email"));
				entity.setPassWord(resultSet.getString("password"));
				entity.setFullName(resultSet.getNString("name"));
				entity.setAvatar(resultSet.getString("avatar"));
				entity.setRoleId(resultSet.getInt("role_id"));
				
				return entity;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public UserDto getUserDtoByEmail(String email) {
		UserDto userDto = null;
		Connection connection = DbConnection.getConnection();
		String query = "SELECT * FROM users INNER JOIN roles On users.role_id = roles.id  WHERE email = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				userDto = new UserDto();
				userDto.setId(resultSet.getInt("id"));
				userDto.setEmail(resultSet.getString("email"));
				userDto.setPassWord(resultSet.getString("password"));
				userDto.setFullName(resultSet.getNString("name"));
				userDto.setAvatar(resultSet.getString("avatar"));
				userDto.setRoleId(resultSet.getInt("role_id"));
				userDto.setRoleName(resultSet.getNString("roles.name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userDto;
	}
}
