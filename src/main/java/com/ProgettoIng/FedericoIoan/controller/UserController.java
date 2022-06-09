package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUser(){
		List<User> users = userService.findUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		User user = userService.findUser(userId);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/users/update-password/{userId}")
	public ResponseEntity<User> updatePwd(@PathVariable Long userId, @RequestBody User userDto){
	 User updatedUser = userService.updatePwd(userId, userDto);
	 return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
	 User deleteUser = userService.deleteUser(userId);
	 return ResponseEntity.ok(deleteUser);
	}

}
