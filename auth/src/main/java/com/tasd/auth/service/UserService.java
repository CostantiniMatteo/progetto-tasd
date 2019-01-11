package com.tasd.auth.service;

import com.tasd.auth.model.User;
import com.tasd.auth.model.UserDto;
import com.tasd.auth.model.UserGeneral;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<User> save(UserGeneral user);
    List<User> findAll();
    void delete(String username);
    User findOne(String username);
    User findById(long id);
    UserDto update(UserDto userDto);
    User findByUsername(String username);
}
