package com.tasd.seekers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasd.seekers.entities.SeekerEntity;

public interface SeekerRepository extends JpaRepository<SeekerEntity, Long> {
	public SeekerEntity findByUsername(String username);
}
