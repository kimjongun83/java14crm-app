package cybersoft.java12.crmapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.service.AuthService;
import cybersoft.java12.crmapp.util.UrlConst;

@WebFilter(urlPatterns = UrlConst.ROOT)
public class AuthFilter implements Filter {
	private AuthService service;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		service = new AuthService();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String servletPath = req.getServletPath();
		if(servletPath.startsWith(UrlConst.ASSETS) || servletPath.startsWith(UrlConst.AUTH_LOGIN))
				chain.doFilter(req, resp);
		else {
//			if(servletPath.startsWith(UrlConst.TASK_DASHBOARD)) {
//				String role = String.valueOf(req.getSession().getAttribute("role"));
//				System.out.println("check");
//				System.out.println(role);
//				if(role != null) {
//					if(role.equals("1")) {
//						chain.doFilter(request, response);
//					}else {
//						resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
//					}
//				}else {
//					resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
//				}
//			}else {
				String status = String.valueOf(req.getSession().getAttribute("status"));
				System.out.println("STATUS: " + status);
				if(status.equals("null"))
					resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
				else
					chain.doFilter(request, response);
//			}

		}
		
	}
}
