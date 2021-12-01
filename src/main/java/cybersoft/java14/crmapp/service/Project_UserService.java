package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.Project_UserDao;
import cybersoft.java12.crmapp.dto.Project_UserCreateDto;
import cybersoft.java12.crmapp.model.Project_User;

public class Project_UserService {
	private Project_UserDao dao;

	public Project_UserService() {
		dao = new Project_UserDao();
	}

	public void addNewStaffToProject(Project_UserCreateDto projectUser) {
		try {
			dao.addNewStaffToProject(projectUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Project_User> findAllStaff() {
		List<Project_User> projectUsers = null;
		try {
			projectUsers = dao.findAllStaff();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectUsers;
	}

	public void removeStaffFromProject(int idToDelete) {
		try {
			dao.removeStaffFromProject(idToDelete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStaffFromProjectByProjectId(int idToDelete) {
		try {
			dao.deleteStaffFromProjectByProjectId(idToDelete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
