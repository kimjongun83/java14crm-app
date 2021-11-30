package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "errocontroller", urlPatterns = {"/403", "/404"})
public class ErroController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/403":
			req.getRequestDispatcher("/WEB-INF/views/erro/403.jsp").forward(req, resp);
			break;
		case "/404":
			req.getRequestDispatcher("/WEB-INF/views/erro/404.jsp").forward(req, resp);
			break;
		default:
			break;
		}
		
	}
}
