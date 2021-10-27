package com.federico_ioan.ProgettoIng.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		return userRepository.save(newUser);
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
		if(userDto.getRole()!= null) {
			userToUpdate.setRole(userDto.getRole());
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
