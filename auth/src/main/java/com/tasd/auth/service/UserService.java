package com.tasd.auth.service;

import com.tasd.auth.model.User;
import com.tasd.auth.model.UserAdmin;
import com.tasd.auth.model.UserCenter;
import com.tasd.auth.model.UserDto;
import com.tasd.auth.model.UserGeneral;
import com.tasd.auth.model.UserSeeker;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<User> save(UserGeneral user);
    List<User> findAll();
    void delete(long id);

    User findOne(String username);

    User findById(long id);

    UserDto update(UserDto userDto);
}
