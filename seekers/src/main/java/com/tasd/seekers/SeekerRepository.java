package com.tasd.seekers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeekerRepository extends JpaRepository<SeekerEntity, Long> {

	public SeekerEntity findByUsername(String username);
	public boolean existsByUsername(String username);
}
