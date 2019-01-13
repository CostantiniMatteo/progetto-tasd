package com.tasd.applications;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationsRepository extends JpaRepository<ApplicationsEntity, Long>  {
	public List<ApplicationsEntity> findAllByUsername(String username);
	public List<ApplicationsEntity> findAllByCenterUsername(String username);
	public List<ApplicationsEntity> findAllByJobId(long jobId);
}
