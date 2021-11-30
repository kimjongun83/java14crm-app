package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;

@WebServlet(name = "rolecontroller", urlPatterns = {"/role", "/role/add", "/role/edit", "/role/delete"})
public class RoleController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleService roleService;
	public RoleController() {
		roleService = new RoleService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/role":
			List<RoleDto> roleDtos = roleService.findAll();
			req.setAttribute("listRoles", roleDtos);
			req.getRequestDispatcher("/WEB-INF/views/role/index.jsp").forward(req, resp);
			break;
		case "/role/add":
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
			break;
		case "/role/edit":
			int idUpdate = Integer.parseInt(req.getParameter("id"));
			RoleDto roleDto = roleService.getRoleDtoById(idUpdate);
			req.setAttribute("roleOld", roleDto);
			req.getRequestDispatcher("/WEB-INF/views/role/edit.jsp").forward(req, resp);
			break;
		case "/role/delete":
			int idDelete = Integer.parseInt(req.getParameter("id_delete"));
			roleService.deleteRoleDto(idDelete);
			resp.sendRedirect(req.getContextPath()+"/role");
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String action = req.getServletPath();
		switch (action) {
		case "/role/add":
			String name = req.getParameter("rolename");
			String description = req.getParameter("description");
			RoleDto roleDto = new RoleDto(name, description);
			boolean checkAdd = roleService.addRoleDto(roleDto);
					
			if(! checkAdd) {
				req.setAttribute("message_add", "Thêm quền mới thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/role");
			}
			
			break;
		case "/role/edit":
			int idUpdate = Integer.parseInt(req.getParameter("id"));
			name = req.getParameter("rolename");
			description = req.getParameter("description");
			roleDto = new RoleDto(name, description);
			roleDto.setId(idUpdate);
			boolean checkUpdate = roleService.updateRoleDTo(idUpdate, roleDto);
			
			if(!checkUpdate) {
				RoleDto roleDtoOld = roleService.getRoleDtoById(idUpdate);
				req.setAttribute("roleOld", roleDtoOld);
				req.setAttribute("message_update", "Chỉnh sửa thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/role/edit.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/role");
			}
			
		default:
			break;
		}
	}
}
