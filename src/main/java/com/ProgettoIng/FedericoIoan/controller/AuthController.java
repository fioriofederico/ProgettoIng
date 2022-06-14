package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.JwtDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserLoginDto;
import com.ProgettoIng.FedericoIoan.model.dto.UserRegistrationDto;
import com.ProgettoIng.FedericoIoan.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthServiceImpl authService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto loginRequest) {
		try {
			JwtDto jwtDto = authService.authenticateUser(loginRequest);
			return ResponseEntity.ok(jwtDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto signUpRequest) {
		try {
			User registeredUser = authService.registerUser(signUpRequest);
			return ResponseEntity.ok(registeredUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
