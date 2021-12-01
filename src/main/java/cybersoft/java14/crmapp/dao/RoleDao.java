package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.RoleCreateDto;
import cybersoft.java12.crmapp.model.Role;

public class RoleDao {

	public List<Role> findAllRole() throws SQLException {
		// TODO Auto-generated method stub
		List<Role> roles = new LinkedList<>();

		Connection connection = MySqlConnection.getConnection();

		String query = "SELECT id, name, description FROM role";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getNString("name"));
				role.setDescription(resultSet.getNString("description"));

				roles.add(role);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return roles;
	}

	public Role findRoleById(int id) throws SQLException {
		Role role = null;
		Connection connection = MySqlConnection.getConnection();

		String query = "SELECT id, name, description FROM role WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getNString("name"));
				role.setDescription(resultSet.getNString("description"));

			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return role;
	}

	public void addNewRole(RoleCreateDto dto) throws SQLException {
		String query = "INSERT INTO role(name, description)" + "VALUES(?,?)";

		Connection connection = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void updateRole(RoleCreateDto dto, int idToUpdate) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "UPDATE role SET name = ?, description = ? WHERE id = ?";
		int result = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getDescription());
			statement.setInt(3, idToUpdate);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void deleteRoleById(int id) throws SQLException {
		String query = "DELETE FROM role WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}
