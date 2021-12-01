package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.dto.ProjectCreateDto;
import cybersoft.java12.crmapp.dto.Project_UserCreateDto;
import cybersoft.java12.crmapp.dto.TaskCreateDto;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.Project_User;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.ProjectService;
import cybersoft.java12.crmapp.service.Project_UserService;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "projectServlet", urlPatterns = { UrlConst.PROJECT_DASHBOARD, UrlConst.PROJECT_MANAGE,
		UrlConst.PROJECT_ADD, UrlConst.PROJECT_UPDATE, UrlConst.PROJECT_DELETE, UrlConst.PROJECT_STAFF,
		UrlConst.PROJECT_STAFF_ADD, UrlConst.PROJECT_STAFF_REMOVE })
public class ProjectServlet extends HttpServlet {
	private ProjectService Pservice;
	private UserService Uservice;
	private Project_UserService PUservice;

	@Override
	public void init() throws ServletException {
		Pservice = new ProjectService();
		Uservice = new UserService();
		PUservice = new Project_UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:
			try {
				getDashboard(req, resp);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_MANAGE:
			getManage(req, resp);
			break;
		case UrlConst.PROJECT_ADD:
			getAdd(req, resp);
			break;
		case UrlConst.PROJECT_DELETE:
			getDelete(req, resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			try {
				getUpdate(req, resp);
			} catch (NumberFormatException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_STAFF:

			try {
				getStaff(req, resp);
			} catch (NumberFormatException | ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}

			break;
		case UrlConst.PROJECT_STAFF_ADD:
			try {
				getStaffAdd(req, resp);
			} catch (NumberFormatException | ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			getStaffRemove(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:

			break;
		case UrlConst.PROJECT_MANAGE:

			break;
		case UrlConst.PROJECT_ADD:
			try {
				postAdd(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_DELETE:

			break;
		case UrlConst.PROJECT_UPDATE:
			try {
				postUpdate(req, resp);
			} catch (NumberFormatException | ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_STAFF:
			try {
				getStaff(req, resp);
			} catch (NumberFormatException | ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			try {
				postStaffAdd(req, resp);
			} catch (NumberFormatException | ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:

			break;
		default:
			break;
		}
	}

	// DASHBOARD
	// ---------------------------------------------------------------------------------

	private void getDashboard(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		List<Project> projects = Pservice.findAllProject();
		if (projects != null || !projects.isEmpty()) {
			req.setAttribute("projects", projects);
		}
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD).forward(req, resp);
	}

	// MANAGE
	// -------------------------------------------------------------------------------------

	private void getManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD).forward(req, resp);
	}

	// ADD
	// ----------------------------------------------------------------------------------------

	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = Uservice.findAll();
		if (users != null || !users.isEmpty()) {
			req.setAttribute("users", users);
		}
		req.getRequestDispatcher(JspConst.PROJECT_ADD).forward(req, resp);
	}

	private void postAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		ProjectCreateDto dto = extractDtoFromReq(req);
		Pservice.add(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}

	// DELETE
	// -----------------------------------------------------------------------------------------

	private void getDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idToDelete = Integer.parseInt(req.getParameter("id"));
		PUservice.deleteStaffFromProjectByProjectId(idToDelete);
		Pservice.deleteById(idToDelete);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}

	// UPDATE
	// ------------------------------------------------------------------------------------------
	private void getUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, NumberFormatException, SQLException {
		int idToUpdate = Integer.parseInt(req.getParameter("id"));
		List<User> users = new LinkedList<User>();
		users = Uservice.sortUserById(idToUpdate);
		req.setAttribute("users", users);
		Project projectToUpdate = Pservice.findProjectById(idToUpdate);
		req.setAttribute("project", projectToUpdate);
		req.getRequestDispatcher(JspConst.PROJECT_UPDATE).forward(req, resp);
	}

	private void postUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, NumberFormatException, SQLException {
		int idToUpdate = Integer.parseInt(req.getParameter("id"));
		ProjectCreateDto dto = extractDtoFromReq(req);
		Pservice.updateProject(dto, idToUpdate);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}

	// STAFF
	// --------------------------------------------------------------------------------------------

	private void getStaff(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, NumberFormatException, SQLException {
		List<Project> projects = Pservice.findAllProject();
		if (projects != null || !projects.isEmpty()) {
			req.setAttribute("projects", projects);
		}
		List<Project_User> projectUsers = PUservice.findAllStaff();
		req.setAttribute("projectUsers", projectUsers);
		req.getRequestDispatcher(JspConst.PROJECT_STAFF).forward(req, resp);
	}

	private void getStaffAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, NumberFormatException, SQLException {
		int idToUpdate = Integer.parseInt(req.getParameter("id"));
		Project projectToAddStaff = Pservice.findProjectById(idToUpdate);
		
		List<User> users = Uservice.findStaffNotJoinProjectStaff(idToUpdate);
		if (users != null || !users.isEmpty()) {
			req.setAttribute("users", users);
		}
		req.setAttribute("project", projectToAddStaff);
		req.getRequestDispatcher(JspConst.PROJECT_STAFFADD).forward(req, resp);
	}

	private void postStaffAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, NumberFormatException, SQLException {
		Project_UserCreateDto puDto = extractProject_UserDtoFromReq(req);
		PUservice.addNewStaffToProject(puDto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF);
	}

	private void getStaffRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idToDelete = Integer.parseInt(req.getParameter("id"));

		PUservice.removeStaffFromProject(idToDelete);

		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF);
	}

	private ProjectCreateDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		int ownerId = Integer.parseInt(req.getParameter("owner"));
		return new ProjectCreateDto(name, description, startDate, endDate, ownerId);

	}
	private Project_UserCreateDto extractProject_UserDtoFromReq(HttpServletRequest req) {
		int projectId = Integer.parseInt(req.getParameter("project_id"));
		int userId = Integer.parseInt(req.getParameter("staff"));
		String joinDate = req.getParameter("join_date");
		return new Project_UserCreateDto(projectId, userId, joinDate);

	}
}
