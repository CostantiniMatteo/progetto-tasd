package com.tasd.auth;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class UserEntity {
	@Id
	private long id;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public enum Role {
		ADMIN,
		SEEKER,
		JOB_CENTER
	}
	
	public UserEntity() {
		
	}
	
	public UserEntity(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	private String username;
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Role getRole() {
		return role;
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
