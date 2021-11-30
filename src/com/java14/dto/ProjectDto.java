package com.myclass.dto;

import java.sql.Date;
import java.util.List;

public class ProjectDto {
	private int id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private int userId;
	private String userName;
	
	private List<UserDto> listUser;

	public ProjectDto() {
		
	}

	public ProjectDto(String name, String description, Date startDate, Date endDate, int userId) {
		
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
	}

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserDto> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserDto> listUser) {
		this.listUser = listUser;
	}
	
	
}
