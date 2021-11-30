package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.ProjectDto;
import com.myclass.dto.UserDto;
import com.myclass.service.ProjectService;
import com.myclass.service.UserService;

@WebServlet(name = "ProjectServlet", urlPatterns = {"/project", "/project/add", "/project/edit", "/project/delete", "/project/listuser"})
public class ProjectController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private UserService userService;
	
	public ProjectController() {
		projectService = new ProjectService();
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/project":
			List<ProjectDto> projectDtos = projectService.getAll();
			req.setAttribute("listProjects", projectDtos);
			req.getRequestDispatcher("/WEB-INF/views/project/index.jsp").forward(req, resp);
			break;
		case "/project/add":
			List<UserDto> listUser = userService.findAll();
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("/WEB-INF/views/project/add.jsp").forward(req, resp);
			break;
		case "/project/edit":
			int idUpdate = Integer.parseInt(req.getParameter("id"));
			ProjectDto projectDto = projectService.getProjectDtoById(idUpdate);
			req.setAttribute("projectOld", projectDto);
			listUser = userService.findAll();
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("/WEB-INF/views/project/edit.jsp").forward(req, resp);
			break;
		case "/project/delete":
			int idDelete = Integer.parseInt(req.getParameter("id_delete"));
			boolean checkDelete = projectService.deleteProjectDto(idDelete);
			if(!checkDelete) {
				req.setAttribute("message_delete", "Xóa dự án thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/project/index.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/project");
			}
			break;
		case "/project/listuser":
			projectDtos = projectService.getAll();
			
			List<UserDto> listUserDtos = null;
			for (ProjectDto temp : projectDtos) {
				listUserDtos =	projectService.getAllUserProject(temp.getId());
				temp.setListUser(listUserDtos);
			}
			req.setAttribute("projectDtoUser", projectDtos);
			req.getRequestDispatcher("/WEB-INF/views/project/userproject.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		switch (action) {
		case "/project/add":
			String name = req.getParameter("projectname");
			String description = req.getParameter("description");
			Date startDate = Date.valueOf(req.getParameter("startdate"));
			Date endDate = Date.valueOf(req.getParameter("enddate"));
			int userId = Integer.parseInt(req.getParameter("userid"));
			
			ProjectDto projectDto = new ProjectDto(name, description, startDate, endDate, userId);
			boolean checkAdd = projectService.addProjectDto(projectDto);
			if(!checkAdd) {
				List<UserDto> listUser = userService.findAll();
				req.setAttribute("listUser", listUser);
				req.setAttribute("message_add", "Thêm mới thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/project/add.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/project");
			}	
			break;
		case "/project/edit":
			int idUpdate = Integer.valueOf(req.getParameter("id"));
			name = req.getParameter("projectname");
			description = req.getParameter("description");
			startDate = Date.valueOf(req.getParameter("startdate"));
			endDate = Date.valueOf(req.getParameter("enddate"));
			userId = Integer.parseInt(req.getParameter("userid"));
			 
			projectDto = new ProjectDto(name, description, startDate, endDate, userId);
			projectDto.setId(idUpdate);
			boolean checkUpdate = projectService.updateProjectDto(idUpdate, projectDto);
			if(!checkUpdate) {
				projectDto = projectService.getProjectDtoById(idUpdate);
				req.setAttribute("projectOld", projectDto);
				List<UserDto> listUser = userService.findAll();
				req.setAttribute("listUser", listUser);
				req.setAttribute("message_update", "Cập nhật thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/project/edit.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/project");
			}
			break;

		default:
			break;
		}
	}
}
