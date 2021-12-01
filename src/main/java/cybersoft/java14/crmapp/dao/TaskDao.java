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
import cybersoft.java12.crmapp.dto.TaskCreateDto;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.model.Status;
import cybersoft.java12.crmapp.model.Task;
import cybersoft.java12.crmapp.model.User;

public class TaskDao {
	private UserDao userDao;
	private ProjectDao projectDao;

	public TaskDao() {
		userDao = new UserDao();
		projectDao = new ProjectDao();
	}

	public List<Task> findAllTask() throws SQLException {
		List<Task> tasks = new LinkedList<>();
		List<User> users = new ArrayList<>();
		List<Status> statuss = new ArrayList<>();
		List<Project> projects = new ArrayList<>();
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT t.id as id, t.name as name, t.description as description, t.start_date as start_date, t.end_date as end_date, "
				+ "u.id as user_id, u.name as user_name, "
				+ "s.id as status_id, s.name as status_name, s.description as status_description, "
				+ "p.id as project_id,  p.name as project_name "
				+ "FROM task t, user u, status s, project p " + "WHERE t.user_id = u.id " + "AND t.project_id = p.id "
				+ "AND t.status_id = s.id "
				+ "ORDER BY t.id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Task task = new Task();

				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setDescription(resultSet.getNString("description"));
				task.setStartDate(resultSet.getDate("start_date"));
				task.setEndDate(resultSet.getDate("end_date"));

				int statusId = resultSet.getInt("status_id");
				Status status = getStatusFromList(statuss, statusId);
				if (status == null) {
					status = new Status();
					status.setId(resultSet.getInt("status_id"));
					status.setName(resultSet.getString("status_name"));
					status.setDescription(resultSet.getString("status_description"));

					statuss.add(status);
				}

				task.setStatus(status);

				int userId = resultSet.getInt("user_id");
				User user = getUserFromList(users, userId);
				if (user == null) {
					user = new User();
					user.setId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("user_name"));

					users.add(user);
				}
				task.setUser(user);

				int projectId = resultSet.getInt("project_id");
				Project project = getProjectFromList(projects, projectId);
				if (project == null) {
					project = new Project();
					project.setId(resultSet.getInt("project_id"));
					project.setName(resultSet.getString("project_name"));

					projects.add(project);
				}
				task.setProject(project);

				tasks.add(task);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return tasks;
	}

	public Task findTaskById(int id) throws SQLException {
		List<User> users = new ArrayList<>();
		List<Status> statuss = new ArrayList<>();
		List<Project> projects = new ArrayList<>();
		Task task = null;
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT t.id as id, t.name as name, t.description as description, t.start_date as start_date, t.end_date as end_date, "
				+ "u.id as user_id, u.name as user_name, "
				+ "s.id as status_id, s.name as status_name, s.description as status_description, "
				+ "p.id as project_id,  p.name as project_name "
				+ "FROM task t, user u, status s, project p " + "WHERE t.user_id = u.id " + "AND t.project_id = p.id "
				+ "AND t.status_id = s.id "
				+ "AND t.id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				task = new Task();

				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setDescription(resultSet.getNString("description"));
				task.setStartDate(resultSet.getDate("start_date"));
				task.setEndDate(resultSet.getDate("end_date"));

				int statusId = resultSet.getInt("status_id");
				Status status = getStatusFromList(statuss, statusId);
				if (status == null) {
					status = new Status();
					status.setId(resultSet.getInt("status_id"));
					status.setName(resultSet.getString("status_name"));
					status.setDescription(resultSet.getString("status_description"));

					statuss.add(status);
				}

				task.setStatus(status);

				int userId = resultSet.getInt("user_id");
				User user = getUserFromList(users, userId);
				if (user == null) {
					user = new User();
					user.setId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("user_name"));

					users.add(user);
				}
				task.setUser(user);

				int projectId = resultSet.getInt("project_id");
				Project project = getProjectFromList(projects, projectId);
				if (project == null) {
					project = new Project();
					project.setId(resultSet.getInt("project_id"));
					project.setName(resultSet.getString("project_name"));

					projects.add(project);
				}
				task.setProject(project);

			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return task;
	}

	public void addNewTask(TaskCreateDto dto) throws SQLException {
		String query = "INSERT INTO task(name, start_date, end_date, status_id, project_id, user_id) " + "VALUES(?,?,?,?,?,?)";

		Connection connection = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setNString(1, dto.getName());
			statement.setString(2, dto.getStartDate());
			statement.setString(3, dto.getEndDate());
			statement.setInt(4, dto.getStatusId());
			statement.setInt(5, dto.getProjectId());
			statement.setInt(6, dto.getUserId());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	public void updateTask(TaskCreateDto dto, int idToUpdate) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "UPDATE task SET name = ?, start_date = ?, end_date = ?, status_id = ?, project_id = ?, user_id = ? WHERE id = ?";
		int result=0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getStartDate());
			statement.setString(3, dto.getEndDate());
			statement.setInt(4, dto.getStatusId());
			statement.setInt(5, dto.getProjectId());
			statement.setInt(6, dto.getUserId());
			statement.setInt(7, idToUpdate);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	private Status getStatusFromList(List<Status> statuss, int statusId) {
		for (Status status : statuss)
			if (status.getId() == statusId)
				return status;
		return null;
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

	public void deleteTaskById(int idToDelete) throws SQLException {
		String query = "DELETE FROM task WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idToDelete);

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
}
