package com.federico_ioan.ProgettoIng.controller;

import com.federico_ioan.ProgettoIng.repository.UserRepository;
import com.federico_ioan.ProgettoIng.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	
	private final UserRepository userRepository;
	
	UserController(UserRepository repository){
		userRepository = repository;
	}
	
	@GetMapping("/users")
	Iterable<User> getUser(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{userId}")
	User getUser(@PathVariable Long userId) {
		return userRepository.findById(userId).orElseThrow();
	}
	
	@PostMapping("/users")
	User createUser(@RequestBody User newUser) {
		return 	userRepository.save(newUser);
	}
	
	@PutMapping("/users/{userId}")
	User updateUser(@PathVariable Long userId, @RequestBody User userDto){
		User userToUpdate = userRepository.findById(userId).orElseThrow();
		if(userDto.getEmail()!= null) {
			userToUpdate.setEmail(userDto.getEmail());
		}
		if(userDto.getName()!= null) {
			userToUpdate.setName(userDto.getName());
		}
		if(userDto.getSurname()!= null) {
			userToUpdate.setSurname(userDto.getSurname());
		}
		if(userDto.getRoles()!= null) {
			userToUpdate.setRoles(userDto.getRoles());
		}
		return userRepository.save(userToUpdate);
	}
	//Aggiornamento Password Utente
	@PutMapping("/users/newPassword/{userId}")
	User updatePwd(@PathVariable Long userId, @RequestBody User userDto){
		User userToUpdate = userRepository.findById(userId).orElseThrow();
		if(userDto.getPassword()!= null) {
			userToUpdate.setPassword(userDto.getPassword());
		}
		return userRepository.save(userToUpdate);
	}
	
	@DeleteMapping("/users/{userId}")
	User deleteUser(@PathVariable Long userId) {
		User user = userRepository.findById(userId).orElseThrow();
		userRepository.delete(user);
		return user;
	}

}
