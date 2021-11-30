package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@WebServlet(name = "usercontroller", urlPatterns = {"/user", "/user/add", "/user/edit", "/user/delete"})
public class UserController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private RoleService roleService;
	public UserController() {
		userService = new UserService();
		roleService = new RoleService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/user":
			List<UserDto> userDtos = userService.findAllUserDto();
			req.setAttribute("listUser", userDtos);
			req.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(req, resp);
			break;
		case "/user/add":
			List<RoleDto> roles = roleService.findAll();
			req.setAttribute("listRole", roles);
			req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			break;
		case "/user/edit":
			int idUpdate = Integer.parseInt(req.getParameter("id"));
			roles = roleService.findAll();
			req.setAttribute("listRole", roles);
			UserDto userDto = userService.getUserDtoById(idUpdate);
			req.setAttribute("userOld", userDto);
			req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			break;
		case "/user/delete":
			int idDelete = Integer.parseInt(req.getParameter("id_delete"));
			userService.deleteUser(idDelete);
			resp.sendRedirect(req.getContextPath()+"/user");
		default:
			break;
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		switch (action) {
		case "/user/add":
			String email = req.getParameter("email");
			String passWord = req.getParameter("password");
			String fullName = req.getParameter("fullname");
			int roleId = Integer.parseInt(req.getParameter("roleid"));
			String avatar = req.getParameter("avatar");
			
			UserDto userDto = new UserDto(email, passWord, fullName, avatar, roleId);
			boolean checkAdd = userService.addUser(userDto);
			if(!checkAdd) {
				req.setAttribute("message_add", "Thêm mới thất bại!");
				List<RoleDto> roles = roleService.findAll();
				req.setAttribute("listRole", roles);
				req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/user");
			}
			break;
		case "/user/edit":
			int idUpdate = Integer.parseInt(req.getParameter("id_update"));
			email = req.getParameter("email");
			passWord = req.getParameter("password");
			fullName = req.getParameter("fullname");
			roleId = Integer.parseInt(req.getParameter("roleid"));
			avatar = req.getParameter("avatar");
			
			userDto = new UserDto(email, passWord, fullName, avatar, roleId);
			userDto.setId(idUpdate);
			boolean checkUpdate = userService.updateUser(idUpdate, userDto);
			if(!checkUpdate) {
				req.setAttribute("message_update", "Cập nhật thông tin không thành công!");
				List<RoleDto>roles = roleService.findAll();
				req.setAttribute("listRole", roles);
				UserDto userDtoOld = userService.getUserDtoById(idUpdate);
				req.setAttribute("userOld", userDtoOld);
				req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
				
				}
			else {
				resp.sendRedirect(req.getContextPath()+"/user");
			}
			
		default:
			break;
		}
	}
}
