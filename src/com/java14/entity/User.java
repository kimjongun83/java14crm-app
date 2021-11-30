package com.myclass.entity;

public class User {

	private int id;
	private String email;
	private String passWord;
	private String fullName;
	private String avatar;
	private int roleId;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String email, String passWord, String fullName, String avatar, int roleId) {
	
		this.email = email;
		this.passWord = passWord;
		this.fullName = fullName;
		this.avatar = avatar;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String numPhone) {
		this.avatar = numPhone;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
}
