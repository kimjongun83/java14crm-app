package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.StatusDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.service.TaskService;
import com.myclass.service.UserService;

@WebServlet(name = "task", urlPatterns = {"/task", "/task/add", "/task/edit", "/task/delete"})
public class TaskController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TaskService taskService;
	private UserService userService;
	
	public TaskController() {
		taskService = new TaskService();
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/task":
			List<TaskDto> taskDtos = taskService.getAll();
			req.setAttribute("listTasks", taskDtos);
			req.getRequestDispatcher("/WEB-INF/views/task/index.jsp").forward(req, resp);
			break;
		case "/task/add":
			List<UserDto> listUser = userService.findAll();
			req.setAttribute("listUser", listUser);
			List<StatusDto> listStatus = taskService.getAllStatus();
			req.setAttribute("listStatus", listStatus);
			req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
			break;
		case "/task/edit":
			listUser = userService.findAll();
			req.setAttribute("listUser", listUser);
			listStatus = taskService.getAllStatus();
			req.setAttribute("listStatus", listStatus);
			int idUpdate = Integer.parseInt(req.getParameter("id"));
			TaskDto taskDto = taskService.getTaskDtoById(idUpdate);
			req.setAttribute("taskOld", taskDto);
			req.getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);
			break;
		case "/task/delete":
			int idDelete = Integer.parseInt(req.getParameter("id_delete"));
			boolean checkDelete = taskService.deleteTaskDto(idDelete);
			if(!checkDelete) {
				req.setAttribute("message_delete", "Xóa không thành công!");
				req.getRequestDispatcher("/WEB-INF/views/task/index.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/task");
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String action = req.getServletPath();
		switch (action) {
		case "/task/add":
			String name = req.getParameter("taskname");
			String description = req.getParameter("description");
			Date startDate = Date.valueOf(req.getParameter("startdate"));
			Date endDate = Date.valueOf(req.getParameter("enddate"));
			int userId = Integer.parseInt(req.getParameter("userid"));
			int projectId = Integer.parseInt(req.getParameter("project_id"));
			int statusId = Integer.parseInt(req.getParameter("statusid"));
			
			TaskDto taskDto = new TaskDto(name, description, startDate, endDate, userId, projectId, statusId);
			boolean checkAdd = taskService.addTaskDto(taskDto);
			if(!checkAdd) {
				List<UserDto> listUser = userService.findAll();
				req.setAttribute("listUser", listUser);
				List<StatusDto> listStatus = taskService.getAllStatus();
				req.setAttribute("listStatus", listStatus);
				req.setAttribute("message_add", "Thêm mới thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/task");
			}
			break;
		case "/task/edit":
			int idUpdate = Integer.parseInt(req.getParameter("id"));
			name = req.getParameter("taskname");
			description = req.getParameter("description");
			startDate = Date.valueOf(req.getParameter("startdate"));
			endDate = Date.valueOf(req.getParameter("enddate"));
			userId = Integer.parseInt(req.getParameter("userid"));
			projectId = Integer.parseInt(req.getParameter("project_id"));
			statusId = Integer.parseInt(req.getParameter("statusid"));
			
			taskDto = new TaskDto(name, description, startDate, endDate, userId, projectId, statusId);
			taskDto.setId(idUpdate);
			boolean checkUpdate = taskService.updateTaskDto(idUpdate, taskDto);
			if(!checkUpdate) {
				List<UserDto> listUser = userService.findAll();
				req.setAttribute("listUser", listUser);
				List<StatusDto> listStatus = taskService.getAllStatus();
				req.setAttribute("listStatus", listStatus);
				req.setAttribute("message_edit", "Cập nhật thông tin thất bại!");
				taskDto = taskService.getTaskDtoById(idUpdate);
				req.setAttribute("taskOld", taskDto);
				req.getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/task");
			}
			break;

		default:
			break;
		}
	}
	
}
