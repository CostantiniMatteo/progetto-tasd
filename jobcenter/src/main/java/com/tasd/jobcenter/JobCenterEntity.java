package com.tasd.jobcenter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class JobCenterEntity {
	@Id
	private long id;

	public long getId() {
		return id;
	}
	
	public JobCenterEntity() {
	}
	
	public JobCenterEntity(long id) {
		this.id = id;
	}
	
}
