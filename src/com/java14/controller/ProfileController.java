package com.myclass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@WebServlet(name = "profilecontroller", urlPatterns = {"/profile", "/profile/edit"})
public class ProfileController extends HttpServlet{
	private RoleService roleService;
	private UserService userService;
	
	public ProfileController() {
		roleService = new RoleService();
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/profile":
			
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("USER_LOGIN");
			req.setAttribute("user", userDto);
			List<RoleDto> listRole = new ArrayList<RoleDto>();
			RoleDto roleDto = roleService.getRoleDtoById(userDto.getRoleId());
			listRole.add(roleDto);
			req.setAttribute("listRole", listRole);
			req.getRequestDispatcher("/WEB-INF/views/profile/index.jsp").forward(req, resp);
			break;
		case "/profile/edit":
			
			session = req.getSession();
			userDto = (UserDto) session.getAttribute("USER_LOGIN");
			req.setAttribute("userOld", userDto);
			if(userDto.getRoleName().equals("ROLE_ADMIN")) {
				listRole = roleService.findAll();
				req.setAttribute("listRole", listRole);
			}else {
				listRole = new ArrayList<RoleDto>();
				roleDto = roleService.getRoleDtoById(userDto.getRoleId());
				listRole.add(roleDto);
				req.setAttribute("listRole", listRole);
			}
			req.getRequestDispatcher("/WEB-INF/views/profile/edit.jsp").forward(req, resp);
			break;

		default:
			break;
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/profile/edit":
			String email = req.getParameter("email");
			String passWord = req.getParameter("password");
			String fullName = req.getParameter("fullname");
			int roleId = Integer.parseInt(req.getParameter("roleid"));
			String avatar = req.getParameter("avatar");
			
			UserDto userDto = new UserDto(email, passWord, fullName, avatar, roleId);
			int idUpdate = Integer.parseInt(req.getParameter("id_update"));
			userDto.setId(idUpdate);
			
			boolean checkUpdate = userService.updateUser(idUpdate, userDto);
			if(!checkUpdate) {
				req.setAttribute("message_edit", "Cập nhật thất bại!");
				HttpSession session = req.getSession();
				userDto = (UserDto) session.getAttribute("USER_LOGIN");
				req.setAttribute("userOld", userDto);
				if(userDto.getRoleName().equals("ROLE_ADMIN")) {
					List<RoleDto> listRole = roleService.findAll();
					req.setAttribute("listRole", listRole);
				}else {
					List<RoleDto> listRole = new ArrayList<RoleDto>();
					RoleDto roleDto = roleService.getRoleDtoById(userDto.getRoleId());
					listRole.add(roleDto);
					req.setAttribute("listRole", listRole);
				}
				req.getRequestDispatcher("/WEB-INF/views/profile/edit.jsp").forward(req, resp);
			}
			else {
//				HttpSession session = req.getSession();
//				session.setAttribute("USER_LOGIN", userDto);
				resp.sendRedirect(req.getContextPath()+"/login");
			}
			break;

		default:
			break;
		}
	}
}
