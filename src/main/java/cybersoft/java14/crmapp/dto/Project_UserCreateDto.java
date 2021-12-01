package cybersoft.java12.crmapp.dto;

public class Project_UserCreateDto {
	private int project;
	private int user;
	private String joinDate;

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}


	public Project_UserCreateDto(int project, int user, String joinDate) {
		this.project = project;
		this.user = user;
		this.joinDate = joinDate;
	}

}
