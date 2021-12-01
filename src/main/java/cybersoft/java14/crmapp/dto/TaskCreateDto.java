package cybersoft.java12.crmapp.dto;


public class TaskCreateDto {
	private String name;
	private String description;
	private String startDate;
	private String endDate;
	private int statusId;
	private int projectId;
	private int userId;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public TaskCreateDto(String name, String description, String start_date, String end_date, int statusId, int projectId,
			int userId) {
		this.name = name;
		this.description = description;
		this.startDate = start_date;
		this.endDate = end_date;
		this.statusId = statusId;
		this.projectId = projectId;
		this.userId = userId;
	}

}
