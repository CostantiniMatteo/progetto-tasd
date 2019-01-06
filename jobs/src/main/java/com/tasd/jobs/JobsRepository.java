package com.tasd.jobs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobsRepository extends JpaRepository<JobEntity, Long>{
    public List<JobEntity> findAllByUsername(String username);
}
