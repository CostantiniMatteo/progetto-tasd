package com.tasd.seekers.skills.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasd.seekers.skills.entities.SkillEntity;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
		
}
