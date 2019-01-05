package com.tasd.auth.proxy;

public class JobCenterEntity {

	private long id;
	private String name;

	public JobCenterEntity() {
	}
	
	public JobCenterEntity(long id, String name) {
		this.id = id;
		this.name = name;
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
