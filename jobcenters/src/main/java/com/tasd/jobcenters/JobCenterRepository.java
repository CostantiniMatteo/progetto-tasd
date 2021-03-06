package com.tasd.jobcenters;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCenterRepository extends JpaRepository<JobCenterEntity, Long>  {

	JobCenterEntity findByName(String name);
	boolean existsByName(String name);
	JobCenterEntity findByUsername(String username);
	void deleteByUsername(String username);
}
