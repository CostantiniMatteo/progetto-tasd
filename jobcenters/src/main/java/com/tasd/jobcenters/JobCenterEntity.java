package com.tasd.jobcenters;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class JobCenterEntity {
	
	@Id
	private long id;

	public JobCenterEntity() {
	}
	
	public JobCenterEntity(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
