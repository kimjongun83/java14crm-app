package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.Project_UserCreateDto;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.Project_User;
import cybersoft.java12.crmapp.model.User;

public class Project_UserDao {

	public List<Project_User> findAllStaff() throws SQLException {
		List<Project_User> projectUsers = new LinkedList<>();
		List<Project> projects = new ArrayList<>();
		List<User> users = new ArrayList<>();
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT pu.join_date, " + "u.id as staff_id, u.name as staff_name, "
				+ "p.id as project_id, p.name as project_name " + "FROM project_user pu, project p, user u "
				+ "WHERE pu.user_id = u.id " + "AND pu.project_id = p.id " + "ORDER BY p.id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Project_User projectUser = new Project_User();

				projectUser.setJoinDate(resultSet.getDate("join_date"));

				int staffId = resultSet.getInt("staff_id");
				User staff = getUserFromList(users, staffId);
				if (staff == null) {
					staff = new User();
					staff.setId(resultSet.getInt("staff_id"));
					staff.setName(resultSet.getString("staff_name"));

					users.add(staff);
				}
				projectUser.setUser(staff);

				int projectId = resultSet.getInt("project_id");
				Project project = getProjectFromList(projects, projectId);
				if (project == null) {
					project = new Project();
					project.setId(resultSet.getInt("project_id"));
					project.setName(resultSet.getString("project_name"));

					projects.add(project);
				}
				projectUser.setProject(project);

				projectUsers.add(projectUser);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return projectUsers;
	}

	public void addNewStaffToProject(Project_UserCreateDto projectUser) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "INSERT INTO project_user(project_id, user_id, join_date ) VALUES(?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, projectUser.getProject());
			statement.setInt(2, projectUser.getUser());
			statement.setString(3, projectUser.getJoinDate());

			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	private Project getProjectFromList(List<Project> projects, int projectId) {
		for (Project project : projects)
			if (project.getId() == projectId)
				return project;
		return null;
	}

	private User getUserFromList(List<User> users, int userId) {
		for (User user : users)
			if (user.getId() == userId)
				return user;
		return null;
	}

	public void removeStaffFromProject(int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "DELETE FROM project_user WHERE user_id = ?";
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
	
	public void deleteStaffFromProjectByProjectId(int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "DELETE FROM project_user WHERE project_id = ?";
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
