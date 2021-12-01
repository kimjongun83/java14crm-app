package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.dto.UserLoginDto;

public class AuthDao {

//	public int login(String email, String password) throws SQLException {
//		Connection connection = MySqlConnection.getConnection();
//		String query = "SELECT count(id) AS result FROM user WHERE email = ? AND password = ?";
//		  
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, email);
//			statement.setString(2, password);
//			
//			ResultSet resultSet = statement.executeQuery();
//			
//			if(!resultSet.next())
//				return 0;
//			
//			return resultSet.getInt(1);
//		} catch (SQLException e) {
//			System.out.println("Unable to connect to database.");
//			e.printStackTrace();
//		} finally {
//			connection.close();
//		}
//		
//		return 0;
//	}

	public UserLoginDto findUserLogin(String email) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT email, password, role_id FROM user WHERE email = ?";
		UserLoginDto dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				dto = new UserLoginDto();
				dto.setEmail(resultSet.getString("email"));
				dto.setPassword(resultSet.getString("password"));
				dto.setRoleId(resultSet.getInt("role_id"));
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return dto;
	}

//	public UserCreateDto findUserByEmail(String email) throws SQLException {
//		Connection connection = MySqlConnection.getConnection();
//		String query = "SELECT email, password, name, role_id FROM user WHERE email = ?";
//		UserCreateDto dto = null;
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, email);
//
//			ResultSet resultSet = statement.executeQuery();
//
//			if (resultSet.next()) {
//				dto = new UserCreateDto();
//				dto.setEmail(resultSet.getString("email"));
//				dto.setPassword(resultSet.getString("password"));
//				dto.setName(resultSet.getString("name"));
//				dto.setAddress(resultSet.getString("address"));
//				dto.setPhone(resultSet.getString("phone"));
//				dto.setRoleId(resultSet.getInt("role_id"));
//			}
//		} catch (SQLException e) {
//			System.out.println("Unable to connect to database.");
//			e.printStackTrace();
//		} finally {
//			connection.close();
//		}
//
//		return dto;
//	}

}
