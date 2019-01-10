package com.tasd.applications;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationsEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String username;
	private Date dateCreation;
	private long jobId;
	
	public ApplicationsEntity(String username, Date dateCreation, long jobId) {
		this.username = username;
		this.dateCreation = dateCreation;
		this.jobId = jobId;
	}

	public ApplicationsEntity(long id) {
		this.id = id;
	}
	public long getJobId() {
		return jobId;
	}
	
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public ApplicationsEntity() {

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

	public void setId(long id) {
		this.id = id;
	}
}
