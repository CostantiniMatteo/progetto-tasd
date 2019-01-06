package com.tasd.auth.proxy;

public class JobCenterEntity {

	private long id;
	private String name;
	private String username;

	public JobCenterEntity() {
	}
	
	public JobCenterEntity(String name, String username) {
		this.name = name;
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
