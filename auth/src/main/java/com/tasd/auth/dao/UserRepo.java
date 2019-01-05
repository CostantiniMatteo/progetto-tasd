package com.tasd.auth.dao;

import com.tasd.auth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}
