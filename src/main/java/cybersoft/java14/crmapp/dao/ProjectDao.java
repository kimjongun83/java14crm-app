package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.ProjectCreateDto;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.User;

public class ProjectDao {
	public List<Project> findAllProject() throws SQLException {
		List<Project> projects = new LinkedList<>();
		List<User> users = new ArrayList<>();
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT p.id as id, p.name as name, p.description as description, p.start_date as start_date, p.end_date as end_date, "
				+ "u.id as owner_id, u.name as owner_name " + "FROM project p, user u " + "WHERE p.owner = u.id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Project project = new Project();

				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setDescription(resultSet.getString("description"));
				project.setStartDate(resultSet.getDate("start_date"));
				project.setEndDate(resultSet.getDate("end_date"));

				int ownerId = resultSet.getInt("owner_id");
				User owner = getUserFromList(users, ownerId);
				if (owner == null) {
					owner = new User();
					owner.setId(resultSet.getInt("owner_id"));
					owner.setName(resultSet.getString("owner_name"));

					users.add(owner);
				}
				project.setOwner(owner);

				projects.add(project);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return projects;
	}

	private User getUserFromList(List<User> users, int ownerId) {
		for (User user : users)
			if (user.getId() == ownerId)
				return user;
		return null;
	}

	public Project findProjectById(int id) throws SQLException {
		List<User> users = new ArrayList<>();
		Project project = null;
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT p.id as id, p.name as name, p.description as description, p.start_date as start_date, p.end_date as end_date, "
				+ "u.id as owner_id, u.name as owner_name " + "FROM project p, user u " + "WHERE p.owner = u.id "
				+ "AND p.id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				project = new Project();

				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setDescription(resultSet.getString("description"));
				project.setStartDate(resultSet.getDate("start_date"));
				project.setEndDate(resultSet.getDate("end_date"));

				int ownerId = resultSet.getInt("owner_id");
				User owner = getUserFromList(users, ownerId);
				if (owner == null) {
					owner = new User();
					owner.setId(resultSet.getInt("owner_id"));
					owner.setName(resultSet.getString("owner_name"));

					users.add(owner);
				}
				project.setOwner(owner);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return project;

	}

	public void add(ProjectCreateDto project) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "INSERT INTO project(name,description,start_date,end_date,owner) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setString(3, project.getStartDate());
			statement.setString(4, project.getEndDate());
			statement.setInt(5, project.getOwner());

			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void udateProject(ProjectCreateDto project, int idToUpdate) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?, owner = ? WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setString(3, project.getStartDate());
			statement.setString(4, project.getEndDate());
			statement.setInt(5, project.getOwner());
			statement.setInt(6, idToUpdate);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void deleteById(int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "DELETE FROM project WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
}
