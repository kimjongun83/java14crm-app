package cybersoft.java12.crmapp.model;

import java.sql.Date;

public class Project_User {
	private Project project;
	private User user;
	private Date joinDate;
	private String roleDescription;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Project_User(Project project, User user, Date joinDate, String roleDescription) {
		this.project = project;
		this.user = user;
		this.joinDate = joinDate;
		this.roleDescription = roleDescription;
	}

	public Project_User() {
		// TODO Auto-generated constructor stub
	}

}
