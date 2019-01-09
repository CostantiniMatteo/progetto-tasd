package com.tasd.skills.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasd.skills.entities.SkillEntity;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

	void deleteAllByUsername(String username);
		
}
