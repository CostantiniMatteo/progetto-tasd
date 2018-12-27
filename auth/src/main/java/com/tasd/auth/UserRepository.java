package com.tasd.auth;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
	public UserEntity findByUsername(String username);
}
