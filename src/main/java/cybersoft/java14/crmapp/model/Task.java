package cybersoft.java12.crmapp.model;

import java.sql.Date;

public class Task {
	private int id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private Status status;
	private Project project;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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

	

//	public Task(int id, String name, String description, Date startDate, Date endDate, Status statusId,
//			Project projectId, User userId) {
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.statusId = statusId;
//		this.projectId = projectId;
//		this.userId = userId;
//	}

}
