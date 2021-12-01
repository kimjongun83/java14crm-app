package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.dto.RoleCreateDto;
import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.RoleService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.ServletConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = ServletConst.ROLE , urlPatterns = {
		UrlConst.ROLE_DASHBOARD,
		UrlConst.ROLE_ADD,
		UrlConst.ROLE_UPDATE,
		UrlConst.ROLE_DELETE
})
public class RoleServlet extends HttpServlet{
	private RoleService service;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		 service = new RoleService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.ROLE_DASHBOARD:
			getRoleDashboard(req, resp);
			break;

		case UrlConst.ROLE_ADD:
			getRoleAdd(req, resp);

			break;

		case UrlConst.ROLE_UPDATE:
			getRoleUpdate(req, resp);

			break;

		case UrlConst.ROLE_DELETE:
			getRoleDelete(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.ROLE_DASHBOARD:
			getRoleDashboard(req, resp);
			break;

		case UrlConst.ROLE_ADD:
			postRoleAdd(req, resp);

			break;

		case UrlConst.ROLE_UPDATE:
			postRoleUpdate(req, resp);

			break;

		case UrlConst.ROLE_DELETE:
			getRoleDelete(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void getRoleDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> roles = service.findAllRole();
		
		if(roles != null && !roles.isEmpty()) {
			req.setAttribute("roles", roles);
		}
		
		req.getRequestDispatcher(JspConst.ROLE_DASHBOARD).forward(req, resp);
	}

	private void getRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.ROLE_ADD).forward(req, resp);
	}

	private void getRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idToUpdate = Integer.parseInt(req.getParameter("id"));
		Role roleToUpdate = service.findRoleById(idToUpdate);
		
		req.setAttribute("role", roleToUpdate);
		req.getRequestDispatcher(JspConst.ROLE_UPDATE).forward(req, resp);
	}

	private void getRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		service.deleteRoleById(id);

		resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
	}

	private void postRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		RoleCreateDto dto = extractDtoFromReq(req);

		service.addNewRole(dto);

		resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
	}

	private RoleCreateDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		return new RoleCreateDto(name, description);
	}

	private void postRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idToUpdate = Integer.parseInt(req.getParameter("id"));
		RoleCreateDto dto = extractDtoFromReq(req);

		service.updateRole(dto, idToUpdate);

		resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
	}
}
