package com.tasd.auth.model;

import java.util.Date;

import com.tasd.auth.model.User.Role;

public class UserSeeker extends UserDto{

	public UserSeeker(String username, String password, Role role) {
		super(username, password, role);
	}
	private String name;
	private String surname;
	private Date birth;
	private String city;
	
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
