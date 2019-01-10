package com.tasd.auth.controller;

import com.tasd.auth.model.ApiResponse;
import com.tasd.auth.model.User;
import com.tasd.auth.model.UserDto;
import com.tasd.auth.model.UserGeneral;
import com.tasd.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController



// TODO: modificare update e usare solo user
// TODO: Testare
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<User>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PutMapping("/users/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity delete(@PathVariable String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<User> saveNewUser(@RequestBody UserGeneral user){
        return userService.save(user);
    }


}
