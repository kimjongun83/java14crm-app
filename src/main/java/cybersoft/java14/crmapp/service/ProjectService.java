package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.ProjectDao;
import cybersoft.java12.crmapp.dto.ProjectCreateDto;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.Task;

public class ProjectService {
	private ProjectDao dao;

	public ProjectService() {
		dao = new ProjectDao();
	}

	public List<Project> findAllProject() {
		List<Project> projects = null;
		try {
			projects = dao.findAllProject();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

	public void add(ProjectCreateDto project) {
		try {
			dao.add(project);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateProject(ProjectCreateDto project, int idToUpdate) {
		try {
			dao.udateProject(project, idToUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Project findProjectById(int idToUpdate) {
		Project project = null;
		try {
			project = dao.findProjectById(idToUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}

}
