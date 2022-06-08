package com.federico_ioan.ProgettoIng.controller;

import com.federico_ioan.ProgettoIng.repository.UserRepository;
import com.federico_ioan.ProgettoIng.model.User;
import com.federico_ioan.ProgettoIng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	

	@Autowired
	UserService userService;

	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUser(){
		return userService.getUser();
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}
	

	@PutMapping("/users/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User user){
		return userService.updateUser(userId, user);
	}
	//Aggiornamento Password Utente
	@PutMapping("/users/newPassword/{userId}")
	public User updatePwd(@PathVariable Long userId, @RequestBody User userDto){
	 return userService.updatePwd(userId, userDto);
	}
	
	@DeleteMapping("/users/{userId}")
	public User deleteUser(@PathVariable Long userId) {
	 return userService.deleteUser(userId);
	}

}
