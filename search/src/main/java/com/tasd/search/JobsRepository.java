package com.tasd.search;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<JobEntity, Long>, JobsRepositoryCustom {
}
