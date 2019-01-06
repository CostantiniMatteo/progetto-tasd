package com.tasd.auth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasd.auth.dao.UserRepo;
import com.tasd.auth.model.User;
import com.tasd.auth.model.UserAdmin;
import com.tasd.auth.model.UserCenter;
import com.tasd.auth.model.UserDto;
import com.tasd.auth.model.UserGeneral;
import com.tasd.auth.model.UserSeeker;
import com.tasd.auth.proxy.CenterEntityProxy;
import com.tasd.auth.proxy.JobCenterEntity;
import com.tasd.auth.service.UserService;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private CenterEntityProxy centerEntityProxy;
	
	@Autowired
	private UserRepo userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(long id) {
		Optional<User> optionalUser = userDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public UserDto update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public ResponseEntity<User> save(UserGeneral user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		if((!userDao.existsByUsername(user.getUsername())) && user.getCenterName() != null && (!centerEntityProxy.existsCenter(user.getCenterName()))) {
			User newUserSave = userDao.save(newUser);
			dispatchUser(user);
			return ResponseEntity.ok().body(newUserSave);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
    }
  
    public void dispatchUser(UserGeneral user) {
    	if(user.getRole().equals(User.Role.JOB_CENTER)) {
    		centerEntityProxy.createCenter(new JobCenterEntity(user.getCenterName(), user.getUsername()));
    	}
    	else if(user.getRole().equals(User.Role.SEEKER)) {
    		
    	}
    	else if(user.getRole().equals(User.Role.ADMIN)) {
    		
    	}
    }
    
}
