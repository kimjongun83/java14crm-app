package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.crmapp.dao.TaskDao;
import cybersoft.java12.crmapp.dto.TaskCreateDto;
import cybersoft.java12.crmapp.model.Task;

public class TaskService {

	private TaskDao taskDao;

	public TaskService() {
		taskDao = new TaskDao();
	}

	public List<Task> findAllTask() {
		List<Task> tasks = null;
		try {
			tasks = taskDao.findAllTask();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public Task findTaskById(int idToUpdate) {
		Task task = null;
		try {
			task = taskDao.findTaskById(idToUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}

	public void addNewTask(TaskCreateDto dto) {
		try {
			taskDao.addNewTask(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTask(TaskCreateDto dto, int idToUpdate) {
		try {
			taskDao.updateTask(dto, idToUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void deleteTaskById(int idToDelete) {
		try {
			taskDao.deleteTaskById(idToDelete);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
