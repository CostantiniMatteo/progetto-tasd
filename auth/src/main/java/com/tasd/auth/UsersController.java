package com.tasd.auth;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@RestController
public class UsersController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
	public ResponseEntity<?> signUp(@RequestBody UserEntity user) {
		System.out.println("COSTA NON è PUCCIOSO");
		if(!validateRole(user)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	public boolean validateRole(UserEntity userEntity) {
		return !userEntity.getRole().equals(UserEntity.Role.ADMIN);
	}
}
