package com.tasd.auth.model;

import com.tasd.auth.model.User.Role;

public class UserAdmin extends UserDto{

	public UserAdmin(String username, String password, Role role) {
		super(username, password, role);
	}
	private String name;
	private String surname;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
