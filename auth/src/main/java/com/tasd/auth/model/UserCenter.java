package com.tasd.auth.model;

import com.tasd.auth.model.User.Role;

public class UserCenter extends UserDto{

	private String centerName;

	public UserCenter(String centerName, String username, String password, Role role) {
		super(username, password, role);
		this.centerName = centerName;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	
	
}
